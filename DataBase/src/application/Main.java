package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	private static String dbURL;

	private static String dbUsername = "root";
	private static String dbPassword = "as1200219";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "GymManagementSystem";
	private static Connection con;
	ArrayList<Member> member = new ArrayList<Member>();
	ArrayList<MemberShip> membership = new ArrayList<MemberShip>();
	ArrayList<Payment> payment = new ArrayList<Payment>();
	ArrayList<Report> report = new ArrayList<Report>();
	ArrayList<Trainor> trainor = new ArrayList<Trainor>();
	ArrayList<Workout> workout = new ArrayList<Workout>();
	ArrayList<Workout_Plan> workoutp = new ArrayList<Workout_Plan>();
	ArrayList<W2MEM> w2mem = new ArrayList<W2MEM>();

	@Override
	public void start(Stage primaryStage) {
		try {
			DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPassword);
			con = a.connectDB();
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(450);
			r.setTranslateY(140);
			r.setFill(Color.grayRgb(1, 0.4));
			Label login = new Label("Login");
			login.setFont(new Font("Century", 70));
			login.setTextFill(Color.WHITE);
			login.setTranslateX(600);
			login.setTranslateY(1);
			Label email = new Label("Email");
			email.setFont(new Font("Century", 20));
			email.setTextFill(Color.WHITE);
			TextField emailt = new TextField();
			HBox h1 = new HBox(email, emailt);
			h1.setSpacing(20);
			Label password = new Label("Password");
			password.setFont(new Font("Century", 20));
			password.setTextFill(Color.WHITE);
			TextField passwordt = new TextField();
			HBox h2 = new HBox(password, passwordt);
			h1.setSpacing(30);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon1.png");
			icon1.setFitHeight(20);
			icon1.setFitWidth(20);
			Button loginb = new Button("Login", icon1);
			loginb.setStyle("-fx-font-size: 1em");
			loginb.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
			VBox v1 = new VBox(h1, h2, loginb);
			v1.setTranslateX(550);
			v1.setTranslateY(350);
			v1.setSpacing(40);
			loginb.setOnAction(e -> {
				String q = "select TId from trainor where email='" + emailt.getText() + "'and passwords='"
						+ passwordt.getText() + "'";
				try {
					Statement st = con.createStatement();
					st = con.createStatement();
					ResultSet rs = st.executeQuery(q);
					if (emailt.getText().isEmpty() || passwordt.getText().isEmpty()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("please enter your email and password!");

						alert.showAndWait();
					} else if (!rs.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("please enter a valid email address and password!");

						alert.showAndWait();

						if (!emailt.getText().contains("@management.com")) {
							Alert alert1 = new Alert(AlertType.ERROR);
							alert1.setTitle("Error Dialog");
							alert1.setHeaderText("Look, an Error Dialog");
							alert1.setContentText("Ooops,you are not allowed to login!");

							alert1.showAndWait();

						}
					}

					else {
						scene2(primaryStage);
					}
					rs.close();

					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			});
			root.getChildren().addAll(r, login, v1);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void scene2(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon2.png");
			icon1.setFitHeight(80);
			icon1.setFitWidth(80);
			Button memb = new Button("Members", icon1);
			memb.setPrefWidth(250);
			memb.setPrefHeight(125);
			memb.setEffect(new DropShadow());
			memb.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon6.png");
			icon2.setFitHeight(80);
			icon2.setFitWidth(80);
			Button trab = new Button("Trainors", icon2);
			trab.setPrefWidth(250);
			trab.setPrefHeight(125);
			trab.setEffect(new DropShadow());
			trab.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon3 = new ImageView("C:\\Users\\image\\icon10.png");
			icon3.setFitHeight(80);
			icon3.setFitWidth(80);
			Button payb = new Button("Payments", icon3);
			payb.setPrefWidth(250);
			payb.setPrefHeight(125);
			payb.setEffect(new DropShadow());
			payb.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon4 = new ImageView("C:\\Users\\image\\icon3.png");
			icon4.setFitHeight(80);
			icon4.setFitWidth(80);
			Button repb = new Button("Reports", icon4);
			repb.setPrefWidth(250);
			repb.setPrefHeight(125);
			repb.setEffect(new DropShadow());
			repb.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon5 = new ImageView("C:\\Users\\image\\icon4.png");
			icon5.setFitHeight(80);
			icon5.setFitWidth(80);
			Button workb = new Button("Workout", icon5);
			workb.setPrefWidth(250);
			workb.setPrefHeight(125);
			workb.setEffect(new DropShadow());
			workb.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon6 = new ImageView("C:\\Users\\image\\icon9.png");
			icon6.setFitHeight(80);
			icon6.setFitWidth(80);
			Button memsb = new Button("Membership", icon6);
			memsb.setPrefWidth(250);
			memsb.setPrefHeight(125);
			memsb.setEffect(new DropShadow());
			memsb.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon11.png");
			icon7.setFitHeight(80);
			icon7.setFitWidth(80);
			Button workbb = new Button("Workout Plan", icon7);
			workbb.setPrefWidth(250);
			workbb.setPrefHeight(125);
			workbb.setEffect(new DropShadow());
			workbb.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon21.png");
			icon8.setFitHeight(80);
			icon8.setFitWidth(80);
			Button logout = new Button("Logout", icon8);
			logout.setPrefWidth(250);
			logout.setPrefHeight(125);
			logout.setEffect(new DropShadow());
			logout.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			HBox h1 = new HBox(memb, memsb, trab, payb);
			HBox h2 = new HBox(repb, workb, workbb, logout);
			h1.setSpacing(50);
			h2.setSpacing(50);
			h1.setAlignment(Pos.CENTER);
			h2.setAlignment(Pos.CENTER);

			VBox v = new VBox(h1, h2);
			v.setSpacing(70);
			Rectangle r = new Rectangle();
			r.setWidth(1300);
			r.setHeight(600);

			r.setFill(Color.grayRgb(1, 0.4));
			root.getChildren().addAll(r, v);
			v.setAlignment(Pos.CENTER);
			root.setAlignment(Pos.CENTER);
			memb.setOnAction(e -> {

				members(primaryStage);

			});
			trab.setOnAction(e -> {

				trainors(primaryStage);

			});
			memsb.setOnAction(e -> {
				membership(primaryStage);
			});
			payb.setOnAction(e -> {
				payment(primaryStage);

			});
			repb.setOnAction(e -> {

				report(primaryStage);

			});
			workb.setOnAction(e -> {

				workout(primaryStage);

			});
			workbb.setOnAction(e -> {

				workout_plan(primaryStage);

			});
			logout.setOnAction(e -> {

				start(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void payment(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon14.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button search = new Button("Search", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon15.png");
			icon8.setFitHeight(40);
			icon8.setFitWidth(40);
			Button print = new Button("Print", icon8);
			print.setPrefWidth(130);
			print.setEffect(new DropShadow());
			print.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(id, search, print);
			VBox v2 = new VBox(tid, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(60);
			h.setTranslateY(20);
			h.setTranslateX(70);
			root.getChildren().addAll(r, h);

			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			search.setOnAction(e -> {
				String q1 = "select MId from  member where MId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Member you chose Doesn`t Exist");

						alert.showAndWait();

					} else {

						String q = "select * from payment where MId='" + Integer.valueOf(tid.getText()) + "'";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(q);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			print.setOnAction(e -> {
				String s = "select * from payment";

				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {

				scene2(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void membership(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);
			Label type = new Label("Type");
			type.setFont(new Font("Century", 30));
			type.setTextFill(Color.WHITE);
			String types[] = { "Yearly", "Monthly", "weekly" };
			ComboBox ttype = new ComboBox(FXCollections.observableArrayList(types));
			ttype.setPrefSize(100, 35);
			Label date = new Label("Date");
			date.setFont(new Font("Century", 30));
			date.setTextFill(Color.WHITE);
			DatePicker d = new DatePicker();
			d.setPrefSize(100, 35);

			Label du = new Label("Duration");
			du.setFont(new Font("Century", 30));
			du.setTextFill(Color.WHITE);
			TextField tdu = new TextField();
			tdu.setPrefSize(60, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon14.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button search = new Button("search", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon16.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button update = new Button("update", icon7);
			update.setPrefWidth(130);
			update.setEffect(new DropShadow());
			update.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon15.png");
			icon2.setFitHeight(40);
			icon2.setFitWidth(40);
			Button print = new Button("print", icon2);
			print.setPrefWidth(130);
			print.setEffect(new DropShadow());
			print.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon3 = new ImageView("C:\\Users\\image\\icon17.png");
			icon3.setFitHeight(40);
			icon3.setFitWidth(40);
			Button back = new Button("Back", icon3);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon4 = new ImageView("C:\\Users\\image\\icon18.png");
			icon4.setFitHeight(40);
			icon4.setFitWidth(40);
			Button pay = new Button("Payment", icon4);
			pay.setPrefWidth(130);
			pay.setEffect(new DropShadow());
			pay.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			VBox v1 = new VBox(id, type, date, du, search, pay, print);
			VBox v2 = new VBox(tid, ttype, d, tdu, update, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(60);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);

			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			ttype.setDisable(true);
			d.setDisable(true);
			tdu.setDisable(true);

			search.setOnAction(e -> {
				ttype.setDisable(false);
				d.setDisable(false);
				tdu.setDisable(false);

				String q = "select * from membership where MId='" + Integer.valueOf(tid.getText()) + "'";
				try {
					Statement st = con.createStatement();
					st = con.createStatement();
					ResultSet rs = st.executeQuery(q);

					while (rs.next()) {
						tid.setText(String.valueOf(rs.getInt(1)));
						ttype.setValue(rs.getString(2));
						d.setValue(rs.getDate(3).toLocalDate());

						tdu.setText(rs.getString(4));

					}

					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				tid.setDisable(true);

			});
			update.setOnAction(e -> {
				java.util.Date date1 = java.util.Date
						.from(d.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

				String q = "update membership set type='" + ttype.getValue() + "', Mdate='" + sqlDate + "', duration='"
						+ tdu.getText() + "'where MId= '" + Integer.parseInt(tid.getText()) + "' ";
				try {

					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "updated");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from membership";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				ttype.setValue(null);
				d.setValue(null);
				tdu.clear();

			});
			pay.setOnAction(e -> {
				payment2(primaryStage, Integer.parseInt(tid.getText()));

			});
			print.setOnAction(e -> {
				String s = "select * from membership";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {
				scene2(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void payment2(Stage primaryStage, int mid) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);
			tid.setDisable(true);
			tid.setText(String.valueOf(mid));
			Label method = new Label("Method");
			method.setFont(new Font("Century", 30));
			method.setTextFill(Color.WHITE);
			String methods[] = { "Card", "Cash" };
			ComboBox tmethod = new ComboBox(FXCollections.observableArrayList(methods));
			tmethod.setPrefSize(100, 35);

			Label am = new Label("Amount");
			am.setFont(new Font("Century", 30));
			am.setTextFill(Color.WHITE);
			TextField tam = new TextField();
			tam.setPrefSize(60, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon20.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(id, am, method, insert);
			VBox v2 = new VBox(tid, tam, tmethod, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(60);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());
			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			insert.setOnAction(e -> {

				String q = "insert into payment (MId,amount,method) values ('" + Integer.parseInt(tid.getText()) + "','"
						+ Double.parseDouble(tam.getText()) + "','" + tmethod.getValue() + "')";
				try {
					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "insereted");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from payment";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				reportup(mid);
			});
			back.setOnAction(e -> {

				membership(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void reportup(int mid) {
		double d = 0;
		String s1 = "select sum(amount) from payment where MId='" + mid + "'";
		Statement stmt1;

		try {
			stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(s1);
			while (rs1.next()) {
				d = rs1.getDouble(1);

			}
			rs1.close();
			stmt1.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String q = "update report set total_amount='" + d + "'where MId= '" + mid + "' ";
		try {
			PreparedStatement st = con.prepareStatement(q);
			int in = st.executeUpdate();
			System.out.println(in + "insereted");
			st.close();

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String s = "select * from report";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				report.add(new Report(rs.getInt(1), rs.getDouble(2)));
			}
			rs.close();
			stmt.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < report.size(); i++) {
			System.out.println(report.get(i).toString());
		}

	}

	private void workout_plan(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);

			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(80);
			icon1.setFitWidth(80);
			Button minsert = new Button("Insert Workout Plan", icon1);
			minsert.setPrefWidth(350);
			minsert.setPrefHeight(125);
			minsert.setEffect(new DropShadow());
			minsert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon13.png");
			icon2.setFitHeight(80);
			icon2.setFitWidth(80);
			Button mdelete = new Button("Delete Workout Plan", icon2);
			mdelete.setPrefWidth(350);
			mdelete.setPrefHeight(125);
			mdelete.setEffect(new DropShadow());
			mdelete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon3 = new ImageView("C:\\Users\\image\\icon16.png");
			icon3.setFitHeight(80);
			icon3.setFitWidth(80);
			Button mupdate = new Button("Update Workout Plan", icon3);
			mupdate.setPrefWidth(350);
			mupdate.setPrefHeight(125);
			mupdate.setEffect(new DropShadow());
			mupdate.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon4 = new ImageView("C:\\Users\\image\\icon14.png");
			icon4.setFitHeight(80);
			icon4.setFitWidth(80);
			Button msearch = new Button("Search Workout Plan", icon4);
			msearch.setPrefWidth(350);
			msearch.setPrefHeight(125);
			msearch.setEffect(new DropShadow());
			msearch.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon5 = new ImageView("C:\\Users\\image\\icon15.png");
			icon5.setFitHeight(80);
			icon5.setFitWidth(80);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(80);
			icon7.setFitWidth(80);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(350);
			back.setPrefHeight(125);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			Button mprint = new Button("Print Workout Plan", icon5);
			mprint.setPrefWidth(300);
			mprint.setPrefHeight(125);
			mprint.setEffect(new DropShadow());
			mprint.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			HBox h1 = new HBox(minsert, mdelete, mupdate);
			HBox h2 = new HBox(msearch, mprint, back);
			h1.setSpacing(60);
			h2.setSpacing(60);
			h1.setAlignment(Pos.CENTER);
			h2.setAlignment(Pos.CENTER);
			Rectangle r = new Rectangle();
			r.setWidth(1300);
			r.setHeight(600);

			r.setFill(Color.grayRgb(1, 0.4));
			VBox v = new VBox(h1, h2);
			v.setAlignment(Pos.CENTER);
			v.setSpacing(60);
			root.getChildren().addAll(r,v);
			root.setAlignment(Pos.CENTER);
			minsert.setOnAction(e -> {

				winsert(primaryStage);

			});

			mdelete.setOnAction(e -> {

				wdelete(primaryStage);

			});
			mupdate.setOnAction(e -> {

				wupdate(primaryStage);

			});
			msearch.setOnAction(e -> {

				wsearch(primaryStage);

			});
			mprint.setOnAction(e -> {

				wprint(primaryStage);

			});
			back.setOnAction(e -> {

				scene2(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void wprint(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			String s = "select * from Workout_Plan";
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(s);
				TableView<ObservableList<String>> table = new TableView<>();
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					TableColumn<ObservableList<String>, String> column = new TableColumn<>(
							metaData.getColumnName(i + 1));
					final int colIndex = i;
					column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
					table.getColumns().add(column);
				}

				// Load the data into the TableView
				ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
				while (rs.next()) {
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						row.add(rs.getString(i));
					}
					data.add(row);
				}

				table.setItems(data);
				table.setTranslateX(500);
				root.getChildren().add(table);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			root.add(back, 1, 8);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			back.setOnAction(e -> {

				workout_plan(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void wsearch(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label wid = new Label("Workout Plan ID");
			wid.setFont(new Font("Century", 30));
			wid.setTextFill(Color.WHITE);
			TextField twid = new TextField();
			twid.setPrefSize(60, 35);
			Label id = new Label("Trainor Id");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);

			Label name = new Label("Workout Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(60, 35);
			Label type = new Label("Workout Plan Type");
			type.setFont(new Font("Century", 30));
			type.setTextFill(Color.WHITE);
			RadioButton tp = new RadioButton("Private");
			RadioButton tn = new RadioButton("Non-Private");
			tp.setPrefSize(100, 100);
			tn.setPrefSize(100, 100);
			ToggleGroup group = new ToggleGroup();
			tp.setToggleGroup(group);
			tn.setToggleGroup(group);
			Label time = new Label("Time");
			time.setFont(new Font("Century", 30));
			time.setTextFill(Color.WHITE);
			TextField timet = new TextField();
			timet.setPrefSize(60, 35);

			Label date = new Label("Date");
			date.setFont(new Font("Century", 30));
			date.setTextFill(Color.WHITE);
			DatePicker d = new DatePicker();
			d.setPrefSize(60, 35);

			Label cap = new Label("Capacity");
			cap.setFont(new Font("Century", 30));
			cap.setTextFill(Color.WHITE);
			TextField tcap = new TextField();
			tcap.setPrefSize(60, 35);

			HBox h1 = new HBox(tp, tn);
			h1.setSpacing(10);

			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon9 = new ImageView("C:\\Users\\image\\icon14.png");
			icon9.setFitHeight(40);
			icon9.setFitWidth(40);
			Button search = new Button("search", icon9);
			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(wid, id, name, type, date, time, cap, search);
			VBox v2 = new VBox(twid, tid, tname, h1, d, timet, tcap, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(50);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(50);
			h.setTranslateX(50);
			root.getChildren().addAll(r, h);
			search.setOnAction(e -> {

				String q = "select * from Workout_Plan where WP_Id=" + Integer.parseInt(twid.getText());
				try {
					Statement st = con.createStatement();
					st = con.createStatement();
					ResultSet rs = st.executeQuery(q);
					while (rs.next()) {
						tid.setText(String.valueOf(rs.getInt(2)));
						tname.setText(rs.getString(3));
						if (rs.getString(4).equals("Private")) {
							tp.setSelected(true);
						} else if (rs.getString(4).equals("Non-Private")) {
							tn.setSelected(true);
						}
						d.setValue(rs.getDate(5).toLocalDate());
						timet.setText(rs.getString(6));
						tcap.setText(rs.getString(7));

					}

					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			back.setOnAction(e -> {

				workout_plan(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void wupdate(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label wid = new Label("Workout Plan ID");
			wid.setFont(new Font("Century", 30));
			wid.setTextFill(Color.WHITE);
			TextField twid = new TextField();
			twid.setPrefSize(100, 35);
			Label id = new Label("Trainor Id");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);

			Label name = new Label("Workout Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(100, 35);
			Label type = new Label("Workout Plan Type");
			type.setFont(new Font("Century", 30));
			type.setTextFill(Color.WHITE);
			RadioButton tp = new RadioButton("Private");
			RadioButton tn = new RadioButton("Non-Private");
			tp.setPrefSize(100, 50);
			tn.setPrefSize(100, 50);
			tp.setTextFill(Color.WHITE);
			tn.setTextFill(Color.WHITE);
			ToggleGroup group = new ToggleGroup();
			tp.setToggleGroup(group);
			tn.setToggleGroup(group);
			Label time = new Label("Time");
			time.setFont(new Font("Century", 30));
			time.setTextFill(Color.WHITE);
			TextField timet = new TextField();
			timet.setPrefSize(100, 35);

			Label date = new Label("Date");
			date.setFont(new Font("Century", 30));
			date.setTextFill(Color.WHITE);
			DatePicker d = new DatePicker();
			d.setPrefSize(100, 35);

			Label cap = new Label("Capacity");
			cap.setFont(new Font("Century", 30));
			cap.setTextFill(Color.WHITE);
			TextField tcap = new TextField();
			tcap.setPrefSize(100, 35);

			HBox h1 = new HBox(tp, tn);
		h1.setSpacing(10);

			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon9 = new ImageView("C:\\Users\\image\\icon14.png");
			icon9.setFitHeight(40);
			icon9.setFitWidth(40);
			Button search = new Button("search", icon9);
			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon10 = new ImageView("C:\\Users\\image\\icon16.png");
			icon10.setFitHeight(40);
			icon10.setFitWidth(40);
			Button update = new Button("update", icon10);
			update.setPrefWidth(130);
			update.setEffect(new DropShadow());
			update.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			HBox h2 = new HBox(name, tname);
			h2.setSpacing(10);
			HBox h8 = new HBox(id, tid);
			h8.setSpacing(10);
			HBox h3 = new HBox(type, h1);
			h3.setSpacing(10);
			HBox h4 = new HBox(date, d);
			h4.setSpacing(10);
			HBox h5 = new HBox(time, timet);
			h5.setSpacing(10);
			HBox h6 = new HBox(cap, tcap);
			h6.setSpacing(10);
			HBox h7 = new HBox(wid, twid);
			h7.setSpacing(10);
			HBox h = new HBox(search,update,back);
			h.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(500);
			r.setHeight(550);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			VBox v1 = new VBox(h7,h8,h2, h3, h4, h5, h6, h);
			v1.setSpacing(55);
			v1.setTranslateX(70);
			v1.setTranslateY(20);
			root.getChildren().addAll(r, v1);

			search.setOnAction(e -> {
				

						String q = "select * from Workout_Plan where WP_Id=" + Integer.parseInt(twid.getText());
						try {
							Statement st = con.createStatement();
							st = con.createStatement();
							ResultSet rs = st.executeQuery(q);
							while (rs.next()) {
								tid.setText(String.valueOf(rs.getInt(2)));
								tname.setText(rs.getString(3));
								if (rs.getString(4).equals("Private")) {
									tp.setSelected(true);
								} else if (rs.getString(4).equals("Non-Private")) {
									tn.setSelected(true);
								}
								d.setValue(rs.getDate(5).toLocalDate());
								timet.setText(rs.getString(6));
								tcap.setText(rs.getString(7));

							}

							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					
			});
			update.setOnAction(e -> {
				java.util.Date date1 = java.util.Date
						.from(d.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
				String string;
				if (tp.isSelected()) {
					string = "Private";
				} else {
					string = "Non-Private";
				}
				String q = "update Workout_Plan set TId='" + Integer.valueOf(tid.getText()) + "', WName='"
						+ tname.getText() + "', Wtype='" + string + "', WP_date ='" + sqlDate + "', WP_time='"
						+ timet.getText() + "', capacity='" + tcap.getText() + "'where WP_Id= '"
						+ Integer.parseInt(twid.getText()) + "' ";
				try {

					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "updated");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String s = "select * from workout_plan";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				twid.clear();
				tid.clear();
				tname.clear();
				d.setValue(null);
				timet.clear();
				tcap.clear();
				tp.setSelected(false);
				tn.setSelected(false);

			});
			back.setOnAction(e -> {

				workout_plan(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void wdelete(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label wid = new Label("Workout Plan ID");
			wid.setFont(new Font("Century", 30));
			wid.setTextFill(Color.WHITE);
			TextField twid = new TextField();
			twid.setPrefSize(60, 35);

			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon13.png");
			icon8.setFitHeight(40);
			icon8.setFitWidth(40);
			Button delete = new Button("delete", icon8);
			delete.setPrefWidth(130);
			delete.setEffect(new DropShadow());
			delete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(wid, delete);
			VBox v2 = new VBox(twid, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(50);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);
			delete.setOnAction(e -> {
				String q1 = "select WP_Id from  trainor where WP_Id='" + Integer.parseInt(twid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Workout Plan you chose Doesn`t Exist");

						alert.showAndWait();

					} else {

						String q = "delete from Workout_Plan where WP_Id=" + Integer.parseInt(twid.getText());
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "deleted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String s = "select * from workout_plan";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						twid.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {

				workout_plan(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void winsert(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Trainor Id");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);

			Label name = new Label("Workout Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(100, 35);
			Label type = new Label("Workout Plan Type");
			type.setFont(new Font("Century", 30));
			type.setTextFill(Color.WHITE);
			RadioButton tp = new RadioButton("Private");
			RadioButton tn = new RadioButton("Non-Private");
			tp.setPrefSize(100, 50);
			tn.setPrefSize(100, 50);
			tp.setTextFill(Color.WHITE);
			tn.setTextFill(Color.WHITE);

			ToggleGroup group = new ToggleGroup();
			tp.setToggleGroup(group);
			tn.setToggleGroup(group);
			Label time = new Label("Time");
			time.setFont(new Font("Century", 30));
			time.setTextFill(Color.WHITE);
			TextField timet = new TextField();
			timet.setPrefSize(100, 35);

			Label date = new Label("Date");
			date.setFont(new Font("Century", 30));
			date.setTextFill(Color.WHITE);
			DatePicker d = new DatePicker();
			d.setPrefSize(100, 35);

			Label cap = new Label("Capacity");
			cap.setFont(new Font("Century", 30));
			cap.setTextFill(Color.WHITE);
			TextField tcap = new TextField();
			tcap.setPrefSize(100, 35);

			HBox h1 = new HBox(tp, tn);
			h1.setSpacing(10);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			//VBox v1 = new VBox(id, name, type, date, time, cap, insert);
			//VBox v2 = new VBox(tid, tname, h1, d, timet, tcap, back);
			//v1.setSpacing(20);
			//v2.setSpacing(20);
			HBox h2 = new HBox(name, tname);
			h2.setSpacing(10);
			HBox h7 = new HBox(id, tid);
			h7.setSpacing(10);
			HBox h3 = new HBox(type, h1);
			h3.setSpacing(10);
			HBox h4 = new HBox(date, d);
			h4.setSpacing(10);
			HBox h5 = new HBox(time, timet);
			h5.setSpacing(10);
			HBox h6 = new HBox(cap, tcap);
			h6.setSpacing(10);
			Rectangle r = new Rectangle();
			r.setWidth(500);
			r.setHeight(550);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(insert,back);
			h.setSpacing(20);
			VBox v1 = new VBox(h7,h2, h3, h4, h5, h6, h);
			v1.setSpacing(55);
			v1.setTranslateX(70);
			v1.setTranslateY(20);
			root.getChildren().addAll(r, v1);

			tp.selectedProperty().addListener((observable, oldvalue, newvalue) -> {

				if (newvalue) {
					tcap.setText("1");
				}

			});
			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());

			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			insert.setOnAction(e -> {
				String q1 = "select TId from  trainor where TId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					String q2 = "select WName from  workout where WName='" + tname.getText() + "'";

					Statement st2;

					st2 = con.createStatement();

					ResultSet rs2 = st2.executeQuery(q2);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the tarinor you chose Doesn`t Exist");

						alert.showAndWait();

					} else if (!rs2.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Workout you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						java.util.Date date1 = java.util.Date
								.from(d.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
						java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
						String string;
						if (tp.isSelected()) {
							string = "Private";
						} else {
							string = "Non-Private";
						}

						String q = "insert into workout_plan (TId,WName,Wtype,WP_date,WP_time,capacity) values ('"
								+ tid.getText() + "','" + tname.getText() + "','" + string + "','" + sqlDate + "','"
								+ timet.getText() + "','" + tcap.getText() + "')";
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "insereted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						String s = "select * from workout_plan";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
						tname.clear();
						d.setValue(null);
						timet.clear();
						tcap.clear();
						tp.setSelected(false);
						tn.setSelected(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {

				workout_plan(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void workout(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label name = new Label("Workout Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(60, 35);

			Label des = new Label("Description");
			des.setFont(new Font("Century", 30));
			des.setTextFill(Color.WHITE);
			TextField tdes = new TextField();
			tdes.setPrefSize(60, 35);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);
			ImageView icon10 = new ImageView("C:\\Users\\image\\icon14.png");
			icon10.setFitHeight(40);
			icon10.setFitWidth(40);
			Button search = new Button("Search", icon10);
			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;   ");
			ImageView icon9 = new ImageView("C:\\Users\\image\\icon16.png");
			icon9.setFitHeight(40);
			icon9.setFitWidth(40);
			Button update = new Button("Update", icon9);
			update.setPrefWidth(130);
			update.setEffect(new DropShadow());
			update.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon13.png");
			icon8.setFitHeight(40);
			icon8.setFitWidth(40);
			Button delete = new Button("Delete", icon8);
			delete.setPrefWidth(130);
			delete.setEffect(new DropShadow());
			delete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon11 = new ImageView("C:\\Users\\image\\icon15.png");
			icon11.setFitHeight(40);
			icon11.setFitWidth(40);
			Button print = new Button("Print", icon11);
			print.setPrefWidth(130);
			print.setEffect(new DropShadow());
			print.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;");
			VBox v1 = new VBox(name, des, insert, delete, print);
			VBox v2 = new VBox(tname, tdes, search, update, back);
			v1.setSpacing(50);
			v2.setSpacing(50);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(60);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());

			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			insert.setOnAction(e -> {
				String q1 = "select WName from  workout where WName='" + tname.getText() + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Workout you chose Exist");

						alert.showAndWait();

					} else {

						String q = "insert into workout  values ('" + tname.getText() + "','" + tdes.getText() + "')";
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "insereted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						String s = "select * from workout";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tname.clear();
						tdes.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {

				scene2(primaryStage);

			});
			delete.setOnAction(e -> {

				String q1 = "select WName from  workout where WName='" + tname.getText() + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Workout you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "delete from workout where WName='" + tname.getText() + "'";
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "deleted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						String s = "select * from workout";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tname.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});

			search.setOnAction(e -> {
				String q1 = "select WName from  workout where WName='" + tname.getText() + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Workout you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "select * from workout where WName='" + tname.getText() + "'";
						try {
							Statement st = con.createStatement();
							st = con.createStatement();
							ResultSet rs = st.executeQuery(q);
							while (rs.next()) {

								tname.setText(rs.getString(1));

								tdes.setText(rs.getString(2));

							}

							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			});
			update.setOnAction(e -> {

				String q = "update workout set WName='" + tname.getText() + "', Wdescription='" + tdes.getText()
						+ "'where WName= '" + tname.getText() + "' ";
				try {

					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "updated");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String s = "select * from workout";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				tname.clear();
				tdes.clear();

			});
			print.setOnAction(e -> {
				String s = "select * from workout";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void report(Stage primaryStage) {

		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon14.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button search = new Button("Search", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon15.png");
			icon8.setFitHeight(40);
			icon8.setFitWidth(40);
			Button print = new Button("Print", icon8);
			print.setPrefWidth(130);
			print.setEffect(new DropShadow());
			print.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(id, search, print);
			VBox v2 = new VBox(tid, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(50);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(60);
			h.setTranslateX(50);
			root.getChildren().addAll(r, h);

			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			search.setOnAction(e -> {
				String q1 = "select MId from  member where MId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Member you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "select * from Report where MId='" + Integer.valueOf(tid.getText()) + "'";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(q);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			print.setOnAction(e -> {
				String s = "select * from report";

				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {

				scene2(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void payment1(Stage primaryStage, int mid) {

		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);
			tid.setDisable(true);
			tid.setText(String.valueOf(mid));
			Label method = new Label("Method");
			method.setFont(new Font("Century", 30));
			method.setTextFill(Color.WHITE);
			String methods[] = { "Card", "Cash" };
			ComboBox tmethod = new ComboBox(FXCollections.observableArrayList(methods));
			tmethod.setPrefSize(100, 35);

			Label am = new Label("Amount");
			am.setFont(new Font("Century", 30));
			am.setTextFill(Color.WHITE);
			TextField tam = new TextField();
			tam.setPrefSize(60, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon20.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(id, am, method, insert);
			VBox v2 = new VBox(tid, tam, tmethod, back);
			v1.setSpacing(20);
			v2.setSpacing(20);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(70);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());
			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			insert.setOnAction(e -> {

				String q = "insert into payment (MId,amount,method) values ('" + Integer.parseInt(tid.getText()) + "','"
						+ Double.parseDouble(tam.getText()) + "','" + tmethod.getValue() + "')";
				try {
					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "insereted");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from payment";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				reportin(mid);
			});
			back.setOnAction(e -> {

				members(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void reportin(int mid) {
		double d = 0;
		String s1 = "select sum(amount) from payment where MId='" + mid + "'";
		Statement stmt1;

		try {
			stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(s1);
			while (rs1.next()) {
				d = rs1.getDouble(1);

			}
			rs1.close();
			stmt1.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String q = "insert into report (MId,total_amount) values ('" + mid + "','" + d + "')";
		try {
			PreparedStatement st = con.prepareStatement(q);
			int in = st.executeUpdate();
			System.out.println(in + "insereted");
			st.close();

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String s = "select * from report";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				report.add(new Report(rs.getInt(1), rs.getDouble(2)));
			}
			rs.close();
			stmt.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < report.size(); i++) {
			System.out.println(report.get(i).toString());
		}

	}

	private void membersh(Stage primaryStage, int mid) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);
			tid.setDisable(true);
			tid.setText(String.valueOf(mid));
			Label type = new Label("Type");
			type.setFont(new Font("Century", 30));
			type.setTextFill(Color.WHITE);
			String types[] = { "Yearly", "Monthly", "weekly" };
			ComboBox ttype = new ComboBox(FXCollections.observableArrayList(types));
			ttype.setPrefSize(100, 35);
			Label date = new Label("Date");
			date.setFont(new Font("Century", 30));
			date.setTextFill(Color.WHITE);
			DatePicker d = new DatePicker();
			d.setPrefSize(100, 35);

			Label du = new Label("Duration");
			du.setFont(new Font("Century", 30));
			du.setTextFill(Color.WHITE);
			TextField tdu = new TextField();
			tdu.setPrefSize(60, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);

			ImageView icon8 = new ImageView("C:\\Users\\image\\icon10.png");
			icon8.setFitHeight(40);
			icon8.setFitWidth(40);
			Button pay = new Button("Payment", icon8);
			pay.setPrefWidth(130);
			pay.setEffect(new DropShadow());
			pay.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(id, type, date, du, insert);
			VBox v2 = new VBox(tid, ttype, d, tdu, pay);
			v1.setSpacing(50);
			v2.setSpacing(50);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(70);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());
			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			insert.setOnAction(e -> {
				java.util.Date date1 = java.util.Date
						.from(d.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

				String q = "insert into membership (MId,type,Mdate,duration) values ('"
						+ Integer.parseInt(tid.getText()) + "','" + ttype.getValue() + "','" + sqlDate + "','"
						+ tdu.getText() + "')";
				try {
					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "insereted");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from membership";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});

			pay.setOnAction(e -> {
				payment1(primaryStage, mid);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void trainors(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);

			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(80);
			icon1.setFitWidth(80);
			Button tinsert = new Button("Insert trainor", icon1);
			tinsert.setPrefWidth(280);
			tinsert.setPrefHeight(125);
			tinsert.setEffect(new DropShadow());
			tinsert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon13.png");
			icon2.setFitHeight(80);
			icon2.setFitWidth(80);
			Button tdelete = new Button("Delete trainor", icon2);
			tdelete.setPrefWidth(280);
			tdelete.setPrefHeight(125);
			tdelete.setEffect(new DropShadow());
			tdelete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon3 = new ImageView("C:\\Users\\image\\icon16.png");
			icon3.setFitHeight(80);
			icon3.setFitWidth(80);
			Button tupdate = new Button("Update trainor", icon3);
			tupdate.setPrefWidth(280);
			tupdate.setPrefHeight(125);
			tupdate.setEffect(new DropShadow());
			tupdate.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon4 = new ImageView("C:\\Users\\image\\icon14.png");
			icon4.setFitHeight(80);
			icon4.setFitWidth(80);
			Button tsearch = new Button("Search trainor", icon4);
			tsearch.setPrefWidth(280);
			tsearch.setPrefHeight(125);
			tsearch.setEffect(new DropShadow());
			tsearch.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon5 = new ImageView("C:\\Users\\image\\icon15.png");
			icon5.setFitHeight(80);
			icon5.setFitWidth(80);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(80);
			icon7.setFitWidth(80);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(250);
			back.setPrefHeight(125);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon15.png");
			icon8.setFitHeight(80);
			icon8.setFitWidth(80);
			Button tprint = new Button("Print trainors", icon8);
			tprint.setPrefWidth(280);
			tprint.setPrefHeight(125);
			tprint.setEffect(new DropShadow());
			tprint.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			HBox h1 = new HBox(tinsert, tdelete, tupdate);
			HBox h2 = new HBox(tsearch, tprint, back);
			h1.setSpacing(50);
			h2.setSpacing(50);
			h1.setAlignment(Pos.CENTER);
			h2.setAlignment(Pos.CENTER);
			VBox v = new VBox(h1, h2);
			v.setSpacing(70);
			Rectangle r = new Rectangle();
			r.setWidth(1300);
			r.setHeight(600);

			r.setFill(Color.grayRgb(1, 0.4));
			v.setAlignment(Pos.CENTER);
			root.getChildren().addAll(r,v);
			root.setAlignment(Pos.CENTER);
			tinsert.setOnAction(e -> {

				tinsert(primaryStage);

			});
			tdelete.setOnAction(e -> {

				tdelete(primaryStage);

			});
			tupdate.setOnAction(e -> {

				tupdate(primaryStage);

			});
			tsearch.setOnAction(e -> {

				tsearch(primaryStage);

			});
			tprint.setOnAction(e -> {

				tprint(primaryStage);

			});

			back.setOnAction(e -> {

				scene2(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tprint(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			String s = "select * from Trainor";
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(s);
				TableView<ObservableList<String>> table = new TableView<>();
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					TableColumn<ObservableList<String>, String> column = new TableColumn<>(
							metaData.getColumnName(i + 1));
					final int colIndex = i;
					column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
					table.getColumns().add(column);
				}

				// Load the data into the TableView
				ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
				while (rs.next()) {
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						row.add(rs.getString(i));
					}
					data.add(row);
				}

				table.setItems(data);
				table.setTranslateX(500);
				root.getChildren().add(table);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			root.add(back, 1, 8);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			back.setOnAction(e -> {

				trainors(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tsearch(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label id = new Label("Trainor ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setOnAction(e -> {

				trainors(primaryStage);

			});
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon13.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button search = new Button("Search", icon1);
			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			HBox h1 = new HBox(id, tid);
			HBox h2 = new HBox(search, back);
			h1.setSpacing(30);
			h2.setSpacing(30);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);

			r.setFill(Color.grayRgb(1, 0.4));
			VBox v = new VBox(h1, h2);
			v.setSpacing(100);
			v.setTranslateX(70);
			v.setTranslateY(20);

			root.getChildren().addAll(r, v);

			search.setOnAction(e -> {
				String q1 = "select TId from  trainor where TId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the tarinor you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "select * from trainor where TId=" + Integer.parseInt(tid.getText());

						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(q);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tupdate(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label id = new Label("Trainor ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);
			Label name = new Label("Trainor Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(100, 35);

			Label gender = new Label("Gender");
			gender.setFont(new Font("Century", 30));
			gender.setTextFill(Color.WHITE);
			RadioButton gf = new RadioButton("Femal");
			RadioButton gm = new RadioButton("Male");
			gf.setPrefSize(100, 50);
			gm.setPrefSize(100, 50);
			gf.setTextFill(Color.WHITE);
			gm.setTextFill(Color.WHITE);
			ToggleGroup group = new ToggleGroup();
			gm.setToggleGroup(group);
			gf.setToggleGroup(group);

			Label cont = new Label("Contact");
			cont.setFont(new Font("Century", 30));
			cont.setTextFill(Color.WHITE);
			TextField tcon = new TextField();
			tcon.setPrefSize(100, 35);

			Label age = new Label("Age");
			age.setFont(new Font("Century", 30));
			age.setTextFill(Color.WHITE);
			TextField tage = new TextField();
			tage.setPrefSize(100, 35);

			Label email = new Label("Email");
			email.setFont(new Font("Century", 30));
			email.setTextFill(Color.WHITE);
			TextField temail = new TextField();
			temail.setPrefSize(100, 35);

			Label pass = new Label("Password");
			pass.setFont(new Font("Century", 30));
			pass.setTextFill(Color.WHITE);
			TextField tpass = new TextField();
			tpass.setPrefSize(100, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon14.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button info = new Button("Search", icon1);
			info.setPrefWidth(130);
			info.setEffect(new DropShadow());
			info.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold; -fx-font-size: 10px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon16.png");
			icon2.setFitHeight(40);
			icon2.setFitWidth(40);
			HBox h1 = new HBox(gf, gm);
			h1.setSpacing(10);
			Button update = new Button("Update", icon2);
			update.setPrefWidth(130);
			update.setEffect(new DropShadow());
			update.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold; -fx-font-size: 10px;");

			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(700);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h2 = new HBox(name, tname);
			h2.setSpacing(10);
			HBox h3 = new HBox(gender, h1);
			h3.setSpacing(10);
			HBox h4 = new HBox(cont, tcon);
			h4.setSpacing(10);
			HBox h5 = new HBox(age, tage);
			h5.setSpacing(10);
			HBox h6 = new HBox(email, temail);
			h6.setSpacing(10);
			HBox h7= new HBox(pass, tpass);
			h7.setSpacing(10);
			HBox h = new HBox(info,update, back);
			h.setSpacing(20);
			HBox h8 = new HBox(id,tid);
			h8.setSpacing(20);
			VBox v1 = new VBox(h8,h2, h3, h4, h5, h6, h7,h);
			v1.setSpacing(30);
			v1.setTranslateX(70);
			v1.setTranslateY(20);
			root.getChildren().addAll(r,v1 );
			tname.setDisable(true);
			gm.setDisable(true);
			gf.setDisable(true);
			tcon.setDisable(true);
			tage.setDisable(true);
			temail.setDisable(true);
			tpass.setDisable(true);
			info.setOnAction(e -> {
				
						String q = "select * from trainor where TId=" + Integer.parseInt(tid.getText());
						try {
							Statement st = con.createStatement();
							st = con.createStatement();
							ResultSet rs = st.executeQuery(q);
							while (rs.next()) {
								tid.setText(String.valueOf(rs.getInt(1)));
								tname.setText(rs.getString(2));
								if (rs.getString(3).equals("F")) {
									gf.setSelected(true);
								} else if (rs.getString(3).equals("M")) {
									gm.setSelected(true);
								}
								tcon.setText(rs.getString(4));
								tage.setText(String.valueOf(rs.getInt(5)));
								temail.setText(rs.getString(6));
								tpass.setText(rs.getString(7));

							}

							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						tid.setDisable(true);
						tname.setDisable(false);
						gm.setDisable(false);
						gf.setDisable(false);
						tcon.setDisable(false);
						tage.setDisable(false);
						temail.setDisable(false);
			
			});
			update.setOnAction(e -> {
				String string;
				if (gf.isSelected()) {
					string = "F";
				} else {
					string = "M";
				}
				String q = "update Trainor set TName='" + tname.getText() + "', gender='" + string + "', contact='"
						+ tcon.getText() + "', age='" + Integer.parseInt(tage.getText()) + "', email='"
						+ temail.getText() + "', passwords='" + tpass.getText() + "'where TId= '"
						+ Integer.parseInt(tid.getText()) + "' ";
				try {

					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "updated");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from trainor";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				tid.clear();
				tid.setDisable(false);
				tname.clear();
				tcon.clear();
				tage.clear();
				temail.clear();
				tpass.clear();
				gm.setSelected(false);
				gf.setSelected(false);
			});
			back.setOnAction(e -> {

				trainors(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tdelete(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label id = new Label("Trainor ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setOnAction(e -> {

				trainors(primaryStage);

			});
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon13.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button delete = new Button("Delete", icon1);
			delete.setPrefWidth(130);
			delete.setEffect(new DropShadow());
			delete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			HBox h1 = new HBox(id, tid);
			HBox h2 = new HBox(delete, back);
			h1.setSpacing(30);
			h2.setSpacing(30);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);

			r.setFill(Color.grayRgb(1, 0.4));
			VBox v = new VBox(h1, h2);
			v.setSpacing(100);
			v.setTranslateX(70);
			v.setTranslateY(20);

			root.getChildren().addAll(r, v);

			delete.setOnAction(e -> {
				String q1 = "select TId from  trainor where TId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Trainor you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "delete from trainor where TId=" + Integer.parseInt(tid.getText());
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "deleted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						String s = "select * from trainor";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tinsert(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label name = new Label("Trainor Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(100, 35);

			Label gender = new Label("Gender");
			gender.setFont(new Font("Century", 30));
			gender.setTextFill(Color.WHITE);
			RadioButton gf = new RadioButton("Femal");
			RadioButton gm = new RadioButton("Male");
			gf.setPrefSize(100, 50);
			gm.setPrefSize(100, 50);
			gf.setTextFill(Color.WHITE);
			gm.setTextFill(Color.WHITE);
			ToggleGroup group = new ToggleGroup();
			gm.setToggleGroup(group);
			gf.setToggleGroup(group);
			Label cont = new Label("Contact");
			cont.setFont(new Font("Century", 30));
			cont.setTextFill(Color.WHITE);
			TextField tcon = new TextField();
			tcon.setPrefSize(100, 35);

			Label age = new Label("Age");
			age.setFont(new Font("Century", 30));
			age.setTextFill(Color.WHITE);
			TextField tage = new TextField();
			tage.setPrefSize(100, 35);

			Label email = new Label("Email");
			email.setFont(new Font("Century", 30));
			email.setTextFill(Color.WHITE);
			TextField temail = new TextField();
			temail.setPrefSize(100, 35);

			Label pass = new Label("Password");
			pass.setFont(new Font("Century", 30));
			pass.setTextFill(Color.WHITE);
			TextField tpass = new TextField();
			tpass.setPrefSize(100, 35);
			HBox h1 = new HBox(gf, gm);
			//h1.setSpacing(10);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			HBox h2 = new HBox(name, tname);
			h2.setSpacing(10);
			HBox h3 = new HBox(gender, h1);
			h3.setSpacing(10);
			HBox h4 = new HBox(cont, tcon);
			h4.setSpacing(10);
			HBox h5 = new HBox(age, tage);
			h5.setSpacing(10);
			HBox h6 = new HBox(email, temail);
			h6.setSpacing(10);
			HBox h7= new HBox(pass, tpass);
			h7.setSpacing(10);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(700);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(insert, back);
			h.setSpacing(20);
			VBox v1 = new VBox(h2, h3, h4, h5, h6, h7,h);
			v1.setSpacing(55);
			v1.setTranslateX(70);
			v1.setTranslateY(20);
			root.getChildren().addAll(r, v1);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());

			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			insert.setOnAction(e -> {
				String string;
				if (gf.isSelected()) {
					string = "F";
				} else {
					string = "M";
				}
				String q = "insert into trainor (TName,gender,contact,age,email,passwords) values ('" + tname.getText()
						+ "','" + string + "','" + tcon.getText() + "','" + Integer.parseInt(tage.getText()) + "','"
						+ temail.getText() + "','" + tpass.getText() + "')";
				try {
					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "insereted");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from trainor";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				tname.clear();
				tcon.clear();
				tage.clear();
				temail.clear();
				tpass.clear();
				gm.setSelected(false);
				gf.setSelected(false);
			});
			back.setOnAction(e -> {

				trainors(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void members(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);

			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(80);
			icon1.setFitWidth(80);
			Button minsert = new Button("Insert Member", icon1);
			minsert.setPrefWidth(280);
			minsert.setPrefHeight(125);
			minsert.setEffect(new DropShadow());
			minsert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon13.png");
			icon2.setFitHeight(80);
			icon2.setFitWidth(80);
			Button mdelete = new Button("Delete Member", icon2);
			mdelete.setPrefWidth(280);
			mdelete.setPrefHeight(125);
			mdelete.setEffect(new DropShadow());
			mdelete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon3 = new ImageView("C:\\Users\\image\\icon16.png");
			icon3.setFitHeight(80);
			icon3.setFitWidth(80);
			Button mupdate = new Button("Update Member", icon3);
			mupdate.setPrefWidth(280);
			mupdate.setPrefHeight(125);
			mupdate.setEffect(new DropShadow());
			mupdate.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon4 = new ImageView("C:\\Users\\image\\icon14.png");
			icon4.setFitHeight(80);
			icon4.setFitWidth(80);
			Button msearch = new Button("Search Member", icon4);
			msearch.setPrefWidth(280);
			msearch.setPrefHeight(125);
			msearch.setEffect(new DropShadow());
			msearch.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon5 = new ImageView("C:\\Users\\image\\icon15.png");
			icon5.setFitHeight(80);
			icon5.setFitWidth(80);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(80);
			icon7.setFitWidth(80);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(280);
			back.setPrefHeight(125);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			Button mprint = new Button("Print Members", icon5);
			mprint.setPrefWidth(280);
			mprint.setPrefHeight(125);
			mprint.setEffect(new DropShadow());
			mprint.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			ImageView icon8 = new ImageView("C:\\Users\\image\\icon19.png");
			icon8.setFitHeight(80);
			icon8.setFitWidth(80);
			Button mreg = new Button("Register Member", icon8);
			mreg.setPrefWidth(280);
			mreg.setPrefHeight(125);
			mreg.setEffect(new DropShadow());
			mreg.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 20px;");
			HBox h1 = new HBox(minsert, mdelete, mupdate, mreg);
			HBox h2 = new HBox(msearch, mprint, back);
			h1.setSpacing(50);
			h2.setSpacing(50);
			h1.setAlignment(Pos.CENTER);
			h2.setAlignment(Pos.CENTER);

			VBox v = new VBox(h1, h2);
			v.setSpacing(70);
			Rectangle r = new Rectangle();
			r.setWidth(1300);
			r.setHeight(600);

			r.setFill(Color.grayRgb(1, 0.4));
			v.setAlignment(Pos.CENTER);
			root.getChildren().addAll(r, v);
			root.setAlignment(Pos.CENTER);
			minsert.setOnAction(e -> {

				minsert(primaryStage);

			});
			mreg.setOnAction(e -> {

				mreg(primaryStage);

			});
			mdelete.setOnAction(e -> {

				mdelete(primaryStage);

			});
			mupdate.setOnAction(e -> {

				mupdate(primaryStage);

			});
			msearch.setOnAction(e -> {

				msearch(primaryStage);

			});
			mprint.setOnAction(e -> {

				mprint(primaryStage);

			});
			back.setOnAction(e -> {

				scene2(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mreg(Stage primaryStage) {

		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);

			Label wid = new Label("Workout Plan ID");
			wid.setFont(new Font("Century", 30));
			wid.setTextFill(Color.WHITE);
			TextField twid = new TextField();
			twid.setPrefSize(100, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("register", icon1);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			VBox v1 = new VBox(id, wid, insert);
			VBox v2 = new VBox(tid, twid, back);
			v1.setSpacing(50);
			v2.setSpacing(50);
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(v1, v2);
			h.setSpacing(60);
			h.setTranslateX(70);
			h.setTranslateY(20);
			root.getChildren().addAll(r, h);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());
			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");

			insert.setOnAction(e -> {
				String q1 = "select MId from  member where MId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					String q2 = "select WP_Id from  workout_plan where WP_Id='" + Integer.parseInt(twid.getText())
							+ "'";

					Statement st2;

					st2 = con.createStatement();

					ResultSet rs2 = st2.executeQuery(q2);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Member you chose Doesn`t Exist");

						alert.showAndWait();

					}

					else if (!rs2.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Workout Plan you chose Doesn`t Exist");

						alert.showAndWait();

					}

					else {

						String q = "insert into WPlan2Memebr (WP_Id,MId) values ('" + Integer.parseInt(twid.getText())
								+ "','" + Integer.parseInt(tid.getText()) + "')";
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "insereted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						String s = "select * from WPlan2Memebr";
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}
						tid.clear();
						twid.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			back.setOnAction(e -> {

				members(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void mprint(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			String s = "select * from Member";
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(s);
				TableView<ObservableList<String>> table = new TableView<>();
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					TableColumn<ObservableList<String>, String> column = new TableColumn<>(
							metaData.getColumnName(i + 1));
					final int colIndex = i;
					column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
					table.getColumns().add(column);
				}

				// Load the data into the TableView
				ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
				while (rs.next()) {
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						row.add(rs.getString(i));
					}
					data.add(row);
				}

				table.setItems(data);
				table.setTranslateX(500);
				root.getChildren().add(table);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			root.add(back, 1, 8);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			back.setOnAction(e -> {

				members(primaryStage);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void msearch(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setOnAction(e -> {

				members(primaryStage);

			});
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon13.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button search = new Button("Search", icon1);
			search.setPrefWidth(130);
			search.setEffect(new DropShadow());
			search.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			HBox h1 = new HBox(id, tid);
			HBox h2 = new HBox(search, back);
			h1.setSpacing(30);
			h2.setSpacing(30);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);

			r.setFill(Color.grayRgb(1, 0.4));
			VBox v = new VBox(h1, h2);
			v.setSpacing(100);
			v.setTranslateX(70);
			v.setTranslateY(20);

			root.getChildren().addAll(r, v);
			search.setOnAction(e -> {
				String q1 = "select MId from  member where MId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Member you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "select * from member where MId=" + Integer.parseInt(tid.getText());
						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(q);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mupdate(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(100, 35);
			Label name = new Label("Member Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(100, 35);

			Label gender = new Label("Gender");
			gender.setFont(new Font("Century", 30));
			gender.setTextFill(Color.WHITE);
			RadioButton gf = new RadioButton("Femal");
			RadioButton gm = new RadioButton("Male");
			gf.setPrefSize(100, 50);
			gm.setPrefSize(100, 50);
			ToggleGroup group = new ToggleGroup();
			gm.setToggleGroup(group);
			gf.setToggleGroup(group);

			Label cont = new Label("Contact");
			cont.setFont(new Font("Century", 30));
			cont.setTextFill(Color.WHITE);
			TextField tcon = new TextField();
			tcon.setPrefSize(100, 35);

			Label age = new Label("Age");
			age.setFont(new Font("Century", 30));
			age.setTextFill(Color.WHITE);
			TextField tage = new TextField();
			tage.setPrefSize(100, 35);

			Label email = new Label("Email");
			email.setFont(new Font("Century", 30));
			email.setTextFill(Color.WHITE);
			TextField temail = new TextField();
			temail.setPrefSize(100, 35);

			ImageView icon1 = new ImageView("C:\\Users\\image\\icon14.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button info = new Button("Search", icon1);
			info.setPrefWidth(130);
			info.setEffect(new DropShadow());
			info.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold; -fx-font-size: 10px;");
			ImageView icon2 = new ImageView("C:\\Users\\image\\icon16.png");
			icon2.setFitHeight(40);
			icon2.setFitWidth(40);
			HBox h1 = new HBox(gf, gm);
			h1.setSpacing(10);
			Button update = new Button("Update", icon2);
			update.setPrefWidth(130);
			update.setEffect(new DropShadow());
			update.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold; -fx-font-size: 10px;");

			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			Rectangle r = new Rectangle();
			r.setWidth(550);
			r.setHeight(600);
			r.setTranslateX(70);
			r.setTranslateY(20);
		
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h7 = new HBox(id, tid);
			h7.setSpacing(10);
			HBox h2 = new HBox(name, tname);
			h2.setSpacing(10);
			HBox h3 = new HBox(gender, h1);
			h3.setSpacing(10);
			HBox h4 = new HBox(cont, tcon);
			h4.setSpacing(10);
			HBox h5 = new HBox(age, tage);
			h5.setSpacing(10);
			HBox h6 = new HBox(email, temail);
			h6.setSpacing(10);
			HBox h = new HBox(info, update,back);
			h.setSpacing(20);
			VBox v1 = new VBox(h7,h2, h3, h4, h5, h6, h);
			v1.setSpacing(40);
			v1.setTranslateX(70);
			v1.setTranslateY(20);
			root.getChildren().addAll(r, v1);
			tname.setDisable(true);
			gm.setDisable(true);
			gf.setDisable(true);
			tcon.setDisable(true);
			tage.setDisable(true);
			temail.setDisable(true);
			info.setOnAction(e -> {

				String q1 = "select MId from  member where MId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Member you chose Doesn`t Exist");

						alert.showAndWait();

					} else {
						String q = "select * from member where MId=" + Integer.parseInt(tid.getText());
						try {
							Statement st = con.createStatement();
							st = con.createStatement();
							ResultSet rs = st.executeQuery(q);
							while (rs.next()) {
								tid.setText(String.valueOf(rs.getInt(1)));
								tname.setText(rs.getString(2));
								if (rs.getString(3).equals("F")) {
									gf.setSelected(true);
								} else if (rs.getString(3).equals("M")) {
									gm.setSelected(true);
								}
								tcon.setText(rs.getString(4));
								tage.setText(String.valueOf(rs.getInt(5)));
								temail.setText(rs.getString(6));

							}

							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						tid.setDisable(true);
						tname.setDisable(false);
						gm.setDisable(false);
						gf.setDisable(false);
						tcon.setDisable(false);
						tage.setDisable(false);
						temail.setDisable(false);
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			});
			update.setOnAction(e -> {
				String string;
				if (gf.isSelected()) {
					string = "F";
				} else {
					string = "M";
				}
				String q = "update member set MName='" + tname.getText() + "', gender='" + string + "', contact='"
						+ tcon.getText() + "', age='" + Integer.parseInt(tage.getText()) + "', email='"
						+ temail.getText() + "'where MId= '" + Integer.parseInt(tid.getText()) + "' ";
				try {

					PreparedStatement st = con.prepareStatement(q);
					int in = st.executeUpdate();
					System.out.println(in + "updated");
					st.close();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String s = "select * from member";
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(s);
					TableView<ObservableList<String>> table = new TableView<>();
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						TableColumn<ObservableList<String>, String> column = new TableColumn<>(
								metaData.getColumnName(i + 1));
						final int colIndex = i;
						column.setCellValueFactory(
								cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
						table.getColumns().add(column);
					}

					// Load the data into the TableView
					ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
					while (rs.next()) {
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							row.add(rs.getString(i));
						}
						data.add(row);
					}

					table.setItems(data);
					table.setTranslateX(700);
					table.setTranslateY(20);
					root.getChildren().add(table);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				tid.clear();
				tid.setDisable(false);
				tname.clear();
				tcon.clear();
				tage.clear();
				temail.clear();
				gm.setSelected(false);
				gf.setSelected(false);
			});
			back.setOnAction(e -> {

				members(primaryStage);

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void mdelete(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			Label id = new Label("Member ID");
			id.setFont(new Font("Century", 30));
			id.setTextFill(Color.WHITE);
			TextField tid = new TextField();
			tid.setPrefSize(60, 35);
			ImageView icon7 = new ImageView("C:\\Users\\image\\icon17.png");
			icon7.setFitHeight(40);
			icon7.setFitWidth(40);
			Button back = new Button("Back", icon7);
			back.setPrefWidth(130);
			back.setEffect(new DropShadow());
			back.setOnAction(e -> {

				members(primaryStage);

			});
			back.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon13.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button delete = new Button("Delete", icon1);
			delete.setPrefWidth(130);
			delete.setEffect(new DropShadow());
			delete.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			HBox h1 = new HBox(id, tid);
			HBox h2 = new HBox(delete, back);
			h1.setSpacing(30);
			h2.setSpacing(30);
			Rectangle r = new Rectangle();
			r.setWidth(450);
			r.setHeight(500);
			r.setTranslateX(70);
			r.setTranslateY(20);

			r.setFill(Color.grayRgb(1, 0.4));
			VBox v = new VBox(h1, h2);
			v.setSpacing(100);
			v.setTranslateX(70);
			v.setTranslateY(20);

			root.getChildren().addAll(r, v);

			delete.setOnAction(e -> {
				String q1 = "select MId from  member where MId='" + Integer.parseInt(tid.getText()) + "'";

				Statement st1;

				try {
					st1 = con.createStatement();

					ResultSet rs1 = st1.executeQuery(q1);
					if (!rs1.next()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Look, an Error Dialog");
						alert.setContentText("the Member you chose Doesn`t Exist");

						alert.showAndWait();

					} else {

						String q = "delete from member where MId=" + Integer.parseInt(tid.getText());
						try {
							PreparedStatement st = con.prepareStatement(q);
							int in = st.executeUpdate();
							System.out.println(in + "deleted");
							st.close();

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String s = "select * from Member";

						Statement stmt;
						try {
							stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(s);
							TableView<ObservableList<String>> table = new TableView<>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 0; i < metaData.getColumnCount(); i++) {
								TableColumn<ObservableList<String>, String> column = new TableColumn<>(
										metaData.getColumnName(i + 1));
								final int colIndex = i;
								column.setCellValueFactory(
										cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
								table.getColumns().add(column);
							}

							// Load the data into the TableView
							ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
							while (rs.next()) {
								ObservableList<String> row = FXCollections.observableArrayList();
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									row.add(rs.getString(i));
								}
								data.add(row);
							}

							table.setItems(data);
							table.setTranslateX(700);
							table.setTranslateY(20);
							root.getChildren().add(table);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

						tid.clear();
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void minsert(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 1360, 700);
			Image img = new Image("C:\\Users\\image\\image.jpg", 1360, 700, false, false);

			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);

			Label name = new Label("Member Name");
			name.setFont(new Font("Century", 30));
			name.setTextFill(Color.WHITE);
			TextField tname = new TextField();
			tname.setPrefSize(100, 35);

			Label gender = new Label("Gender");
			gender.setFont(new Font("Century", 30));
			gender.setTextFill(Color.WHITE);
			RadioButton gf = new RadioButton("Female");
			RadioButton gm = new RadioButton("Male");
			gf.setTextFill(Color.WHITE);
			gm.setTextFill(Color.WHITE);

			gf.setPrefSize(100,50);
			gm.setPrefSize(100, 50);
			ToggleGroup group = new ToggleGroup();
			gm.setToggleGroup(group);
			gf.setToggleGroup(group);

			Label cont = new Label("Contact");
			cont.setFont(new Font("Century", 30));
			cont.setTextFill(Color.WHITE);
			TextField tcon = new TextField();
			tcon.setPrefSize(100, 35);

			Label age = new Label("Age");
			age.setFont(new Font("Century", 30));
			age.setTextFill(Color.WHITE);
			TextField tage = new TextField();
			tage.setPrefSize(100, 35);

			Label email = new Label("Email");
			email.setFont(new Font("Century", 30));
			email.setTextFill(Color.WHITE);
			TextField temail = new TextField();
			temail.setPrefSize(100, 35);

			HBox h1 = new HBox(gf, gm);
			//h1.setSpacing(10);
			ImageView icon1 = new ImageView("C:\\Users\\image\\icon12.png");
			icon1.setFitHeight(40);
			icon1.setFitWidth(40);
			Button insert = new Button("Insert", icon1);

			ImageView icon8 = new ImageView("C:\\Users\\image\\icon9.png");
			icon8.setFitHeight(40);
			icon8.setFitWidth(40);
			Button mems = new Button("MemberShip", icon8);
			mems.setPrefWidth(130);
			mems.setEffect(new DropShadow());
			mems.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			// VBox v1 = new VBox(name, gender, cont, age, email, insert);
			// VBox v2 = new VBox(tname, h1, tcon, tage, temail, mems);
			HBox h2 = new HBox(name, tname);
			h2.setSpacing(10);
			HBox h3 = new HBox(gender, h1);
			h3.setSpacing(10);
			HBox h4 = new HBox(cont, tcon);
			h4.setSpacing(10);
			HBox h5 = new HBox(age, tage);
			h5.setSpacing(10);
			HBox h6 = new HBox(email, temail);
			h6.setSpacing(10);

			Rectangle r = new Rectangle();
			r.setWidth(500);
			r.setHeight(550);
			r.setTranslateX(70);
			r.setTranslateY(20);
			r.setFill(Color.grayRgb(1, 0.4));
			HBox h = new HBox(insert, mems);
			h.setSpacing(20);
			VBox v1 = new VBox(h2, h3, h4, h5, h6, h);
			v1.setSpacing(55);
			v1.setTranslateX(70);
			v1.setTranslateY(20);
			root.getChildren().addAll(r, v1);

			insert.setPrefWidth(130);
			insert.setEffect(new DropShadow());

			insert.setStyle(
					"-fx-background-color: #20B2AA; -fx-background-radius: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;    -fx-font-size: 10px;");
			insert.setOnAction(e -> {

				if (!temail.getText().contains(".com")) {
					Alert alert1 = new Alert(AlertType.ERROR);
					alert1.setTitle("Error Dialog");
					alert1.setHeaderText("Look, an Error Dialog");
					alert1.setContentText("Ooops,Invalid Email Address!");

					alert1.showAndWait();

				} else {

					String string;
					if (gf.isSelected()) {
						string = "F";
					} else {
						string = "M";
					}
					String q = "insert into member (MName,gender,contact,age,email) values ('" + tname.getText() + "','"
							+ string + "','" + tcon.getText() + "','" + Integer.parseInt(tage.getText()) + "','"
							+ temail.getText() + "')";
					try {
						PreparedStatement st = con.prepareStatement(q);
						int in = st.executeUpdate();
						System.out.println(in + "insereted");
						st.close();

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					String s = "select * from Member";
					Statement stmt;
					try {
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(s);
						TableView<ObservableList<String>> table = new TableView<>();
						ResultSetMetaData metaData = rs.getMetaData();
						for (int i = 0; i < metaData.getColumnCount(); i++) {
							TableColumn<ObservableList<String>, String> column = new TableColumn<>(
									metaData.getColumnName(i + 1));
							final int colIndex = i;
							column.setCellValueFactory(
									cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
							table.getColumns().add(column);
						}

						// Load the data into the TableView
						ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
						while (rs.next()) {
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= metaData.getColumnCount(); i++) {
								row.add(rs.getString(i));
							}
							data.add(row);
						}

						table.setItems(data);
						table.setTranslateX(700);
						table.setTranslateY(20);

						root.getChildren().add(table);

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					tname.clear();
					tcon.clear();
					tage.clear();
					temail.clear();
					gm.setSelected(false);
					gf.setSelected(false);
				}
			});
			mems.setOnAction(e -> {
				String s1 = "select max(MId) from member";
				Statement stmt1;
				int mid = 0;
				try {
					stmt1 = con.createStatement();
					ResultSet rs1 = stmt1.executeQuery(s1);
					while (rs1.next()) {
						mid = rs1.getInt(1);
					}
					rs1.close();
					stmt1.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				membersh(primaryStage, mid);

			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Connection connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		System.out.println(dbURL);

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);

		return con;
	}
}
