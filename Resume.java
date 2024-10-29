import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.geometry.*;
public class Resume extends Application
{
	public void start(Stage myStage)
	{
		myStage.setTitle("Resume");
		GridPane rootNode = new GridPane();
		rootNode.setHgap(10);
		rootNode.setVgap(10);
		rootNode.setAlignment(Pos.CENTER);
		Canvas c = new Canvas(400, 300);
		GraphicsContext gc = c.getGraphicsContext2D();
		Label name = new Label("Name: ");
		Label qua = new Label("Qualification: ");
		Label langu = new Label("Languages: ");
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		Button b = new Button("Submit");
		Label res = new Label();
		rootNode.add(c, 0, 0, 10, 10);
		rootNode.add(name, 0, 0);
		rootNode.add(qua, 0, 1);
		rootNode.add(langu, 0, 2);
		rootNode.add(t1, 1, 0);
		rootNode.add(t2, 1, 1);
		CheckBox l1 = new CheckBox("English");
		rootNode.add(l1, 1, 2);
		CheckBox l2 = new CheckBox("Hindi");
		rootNode.add(l2, 1, 3);
		CheckBox l3 = new CheckBox("Tamil");
		rootNode.add(l3, 1, 4);
		CheckBox l4 = new CheckBox("Telugu");
		rootNode.add(l4, 1, 5);
		rootNode.add(b, 1, 6);
		b.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e)
			{
				if(!t1.getText().isEmpty() && !t2.getText().isEmpty())
				{
					String selected = new String();
					if(l4.isSelected())
						selected += "Telugu ";
					if(l3.isSelected())
						selected += "Tamil ";
					if(l2.isSelected())
						selected += "Hindi ";
					if(l1.isSelected())
						selected += "English ";
					res.setText("Name: " + t1.getText() + "\nQualification: " + t2.getText() + "\nLanguages: " + selected);
				}
			}
		});
		rootNode.add(res, 0, 3);
		Scene myScene = new Scene(rootNode);
		myStage.setScene(myScene);
		myStage.show();
	}
	public static void main(String args[])
	{
		launch(args);
	}
}