import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.collections.*;
class MyThread1 extends Thread
{
	String str;
	MyThread1(String s)
	{
		str = s;
		start();
	}
	public void run()
	{
		String snew;
		snew = str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
		str = snew;
	}
}
class MyThread2 extends Thread
{
	String str;
	MyThread2(String s)
	{
		str = s;
		start();
	}
	public void run()
	{
		String snew;
		snew = str.substring(1, str.length()) + str.charAt(0);
		str = snew;
	}
}
public class ThreadDemo extends Application
{
	public void start(Stage myStage)
	{
		myStage.setTitle("Thread Demo");
		GridPane rootNode = new GridPane();
		rootNode.setHgap(10);
		rootNode.setVgap(10);
		rootNode.setAlignment(Pos.CENTER);
		Label l1 = new Label("Enter a string: ");
		Label res = new Label();
		TextField t = new TextField();
		RadioButton b1 = new RadioButton("Right to left");
		RadioButton b2 = new RadioButton("Left to right");
		ToggleGroup tg = new ToggleGroup();
		b1.setToggleGroup(tg);
		b2.setToggleGroup(tg);
		rootNode.add(l1, 0, 0);
		rootNode.add(t, 1, 0);
		rootNode.add(b1, 0, 1);
		rootNode.add(b2, 1, 1);
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
			{
				RadioButton rb = (RadioButton)tg.getSelectedToggle();
				if(rb != null){
					String s = rb.getText();
					if(s.equals("Right to left")){
						MyThread1 t1 = new MyThread1(t.getText());
						res.setText(t1.str);
					}
					else{
						MyThread2 t2 = new MyThread2(t.getText());
						res.setText(t2.str);
					}
				}
			}
		});
		rootNode.add(res, 2, 2);
		Scene myScene = new Scene(rootNode, 400, 300);
		myStage.setScene(myScene);
		myStage.show();
	}
	public static void main(String args[])
	{
		launch(args);
	}
}