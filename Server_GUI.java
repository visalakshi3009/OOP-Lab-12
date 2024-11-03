import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.canvas.*;
import java.io.*;
import java.net.*;

class MyThread extends Thread
{
	Server s;
	VBox chatBox;
	MyThread(Server t, VBox v)
	{
		s = t;
		chatBox = v;
		start();
	}
	public void run()
	{
		String m = new String();
		while(true)
		{
			try{
				m = s.receiveMessage();
			}
			catch(IOException e)
			{
				System.out.println("Error 1");
				e.printStackTrace();
			}
			if(m != null)
			{
				String finalMessage = m;
				Platform.runLater(() -> {
					Label received = new Label(finalMessage);
					received.setStyle("-fx-background-color: #FFFFFF; " + // White background
                           "-fx-padding: 10px; " +
                           "-fx-background-radius: 15px; " +    // Rounded background for bubble effect
                           "-fx-border-radius: 15px; " +        // Rounded border corners
                           "-fx-border-color: lightgray; " +    // Light gray border
                           "-fx-border-width: 1px; " +          // Thin border width
                           "-fx-text-fill: #2B3A42;");          // Text color for readability

					HBox messagec = new HBox(received);
        			messagec.setPadding(new Insets(5));
        			messagec.setAlignment(Pos.CENTER_LEFT);

        			chatBox.getChildren().add(messagec);
				});
			}
		}
	}
}
class Server
{
	String send, receive;
	ServerSocket ss;
	Socket s;
	PrintStream ps;
	BufferedReader br;
	BufferedReader inp;
	Server() throws IOException
	{
		ss = new ServerSocket(5001);
		s = ss.accept();
		System.out.println("Connection established");
		ps = new PrintStream(s.getOutputStream());
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		inp = new BufferedReader(new InputStreamReader(System.in));
	}
	void sendMessage(String message) throws IOException
	{
		send = message;
		if(send != null)
			ps.println(send);
		else
			ps.println("Could not send message");
	}
	String receiveMessage() throws IOException
	{
		receive = br.readLine();
		System.out.println(receive);
		if(receive == null)
			return "Could not retrieve message";
		return receive;

	}
	void endServer() throws IOException
	{
		ps.close();
		br.close();
		inp.close();
		s.close();
		ss.close();
		System.exit(0);
	}
}

public class Server_GUI extends Application
{
    Server s;	
	VBox chatBox;
    public void start(Stage myStage)
    {
        myStage.setTitle("Doctor communication portal");
		try{
			s = new Server();
		}
		catch(IOException e)
		{
			System.out.println("Error 2");
			e.printStackTrace();
		}
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setAlignment(Pos.TOP_LEFT);

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		Button send = new Button("Send");
        TextField sendText = new TextField();
        HBox inputArea = new HBox(10, sendText, send);
        inputArea.setPadding(new Insets(10));

        BorderPane rootNode = new BorderPane();
        rootNode.setCenter(scrollPane);
        rootNode.setBottom(inputArea);

        send.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
				try{
					s.sendMessage(sendText.getText());
				}
				catch(IOException e1)
				{
					System.out.println("Error 3");
					e1.printStackTrace();
				}
				Label text = new Label();
				text.setText(sendText.getText());
				sendText.clear();
				text.setStyle("-fx-background-color: #DCF8C6; " +  // Light green background for chat bubble
                           "-fx-text-fill: #2A3A2F; " +         // Darker text color
                           "-fx-padding: 10px; " +              // Padding inside the label
                           "-fx-background-radius: 15px; " +    // Rounded background for bubble effect
                           "-fx-border-radius: 15px; " +        // Border rounding to match background
                           "-fx-border-width: 0; " +            // Remove the border width if desired
                           "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 2, 2);"); // Drop shadow for 3D effect

				HBox messageContainer = new HBox(text);
        		messageContainer.setPadding(new Insets(5));
        		messageContainer.setAlignment(Pos.CENTER_RIGHT);

        		chatBox.getChildren().add(messageContainer);

            }
        });

		MyThread t1 = new MyThread(s, chatBox);

		Scene myScene = new Scene(rootNode);
		myStage.setScene(myScene);
		myStage.show();

    }
	public static void main(String args[])
	{
		Application.launch(Server_GUI.class, args);
	}
}