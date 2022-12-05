package blinker;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//import org.vision.popol.blinker.ConnectionFactory;
//import org.vision.popol.blinker.Blinker;
//import org.vision.popol.blinker.BlinkerDao;
//import org.vision.popol.blinker.TableViewFactory;
import blinker.ConnectionFactory;
import blinker.Blinker;
import blinker.BlinkerDao;
import blinker.TableViewFactory;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Controll  implements Initializable {
	
	public Connection conn = null;
	{
		try {
			conn = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  @FXML
	  private BorderPane borderPane;

    @FXML
    private TextField txtWord;

    @FXML
    private TextField txtMean;

    @FXML
    private TextField txtArea;
    
    
    @FXML
    private Button btnInsert;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Map<String,String>> tableView;
  
    @FXML
    private Button btnStart;

    @FXML
    private Button btnEnd;

    @FXML
    private Button btnExit;
    
    private boolean isStop;
    
    public void refreshTableview() {
    	
    	try {
			tableView = TableViewFactory.getTable("SELECT * FROM BLINKER", ConnectionFactory.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	borderPane.setCenter(tableView);

    }
    	
       
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    // TODO 컨트롤스(컴포넌트)들의 초기화 작업
    // 가계부 디비의 모든자료를 읽어 tableView를 만들어 붙이는 작업
    	refreshTableview();
    }

    
 
    @FXML
    void onInsert(ActionEvent event) {

    	BlinkerDao dao = new BlinkerDao();
    	Blinker vo = new Blinker();
    		vo.setWord(txtWord.getText());
    		vo.setMean(txtMean.getText());
    		try {
    			dao.insert(ConnectionFactory.getConnection(), vo);
    			refreshTableview();
    			} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			}
    }
 

    @FXML
    void onDelete(ActionEvent event) {
    	Map<String, String> map = (Map<String, String>) tableView.getSelectionModel().getSelectedItem();
    	System.out.println(map);
    	BlinkerDao dao = new BlinkerDao();
    	try {
    	// db삭제 삭제작업
    	dao.delete(ConnectionFactory.getConnection(), (map.get("WORD")));
    	refreshTableview();
    	} catch (NumberFormatException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	} catch (SQLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    	}
    	}


    @FXML
    void onStart(ActionEvent event) {
    	
    	isStop = false;// 중요
    	Blinker vo = new Blinker();
    	
    	Thread thread = new Thread() {

			public void run() {
		
				
			    	
					while (!isStop) {
						
						
							tableView = TableViewFactory.getTable("select * from(select * from blinker order by DBMS_RANDOM.RANDOM ) where rownum < 2", conn);
		
							
							//System.out.println(tableView.sel);
							//vo.setWord(txtWord.getText());
				    		//vo.setMean(txtMean.getText());
						
						
					 Platform.runLater(new Runnable() {

						    @Override

						    public void run(){

						       // UI 생성 및 변경 코드
						      //txtArea.setText(tableView);
						    	Map<String,String> x = tableView.getItems().get(0);
								
						    	
						    	txtArea.setText(null);
								txtArea.appendText(x.get("WORD"));
								txtArea.appendText(" : "+x.get("MEAN"));	
						    	
						    	
						   }

						});	
					 try { Thread.sleep(300); } catch (InterruptedException e) {}
					 txtArea.setText(null);
					 try { Thread.sleep(100); } catch (InterruptedException e) {}
					
					}
			}
			};
	
			thread.setDaemon(true);// main 쓰레드와 생명주기를 맞춘다. 
			thread.start();
}
        	
   	
    @FXML
    void onEnd(ActionEvent event) {
    	isStop =  true;
    	refreshTableview();
    }

    @FXML
    void onExit(ActionEvent event) {
    	System.exit(0);
    }

 
}
