import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.image.*;
public class Calculator extends Application
{
	public void start(Stage myStage)
	{
		myStage.setTitle("Calculator");
		GridPane rootNode = new GridPane();
		rootNode.setHgap(10);
		rootNode.setVgap(10);
		rootNode.setAlignment(Pos.CENTER);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		Label num1 = new Label("Number 1: ");
		Label num2 = new Label("Number 2: ");
		Label res = new Label();
		Image i = new Image("1011863.png");
		ImageView v = new ImageView(i);
		v.setFitWidth(50);
		v.setFitHeight(50);
		v.setPreserveRatio(true);
		Button b = new Button("Calculate", v);
		Canvas c = new Canvas(400, 300);
		GraphicsContext ge = c.getGraphicsContext2D();
		rootNode.add(c, 0, 0, 2, 2);
		rootNode.add(num1, 0, 0);
		rootNode.add(num2, 0, 1);
		rootNode.add(t1, 1, 0);
		rootNode.add(t2, 1, 1);
		rootNode.add(b, 0, 2);
		b.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ae)
			{
				if(!t1.getText().isEmpty() && !t2.getText().isEmpty()){
					double sum, diff, pro, quo, a, b;
					a = Double.parseDouble(t1.getText());
					b = Double.parseDouble(t2.getText());
					sum = a + b;
					diff = a - b;
					pro = a * b;
					quo = a / b;
					res.setText("Sum is: " + sum + "\nDifference is: " + diff + "\nProduct is: " + pro + "\nQuotient is: " + quo);
				}
			}
		});
		rootNode.add(res, 1, 2, 2, 1);
		Scene myScene = new Scene(rootNode, 400, 300);
		myStage.setScene(myScene);
		myStage.show();
	}
	public static void main(String args[])
	{
		launch(args);
	}
}