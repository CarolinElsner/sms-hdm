package de.hdm.sms.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.sms.shared.bo.Component;
import de.hdm.sms.shared.bo.Module;

public class SearchResult extends VerticalPanel {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	private DockPanel dockPanel = new DockPanel();
	private VerticalPanel buttonPanel = new VerticalPanel();
	private VerticalPanel modulePanel = new VerticalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private HTML moduleTohtml = new HTML();
	private HTML elementTohtml = new HTML();
	private HTML convTohtml = new HTML();
	private Button deleteButton = new Button("L&oumlschen");
	private Button editButton = new Button("Bearbeiten");
	private Label searchResultLabel = new Label("Suchergebnis");
	private Label errorLabel = new Label("Die Komponente konnte nicht gefunden werden!");
	private Label nameLabel = new Label(); 
	private Label endproductLabel = new Label();
	private String name;
	private DialogBox dialogBox = new DialogBox();
	private Boolean result;
	
	public SearchResult(Boolean result){
		this.result = result;
		
	}
	
	public SearchResult(Boolean result, String nameLabel){
		this.result = result;
		this.name = nameLabel;
		this.nameLabel.setText("Name: " + nameLabel);
		
	}
	
	public void show(){
		
		greetingService.getTypeOfComponent(name,
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {

					}

					public void onSuccess(String result) {
						
						if (result.equals("module")){
							
							Component c1 = new Component("Brett","Aus feinem Edelholz mit schn�rkel","Holz");
							Component c2 = new Component("Nagel","Spitz und lang","Stahl");
							Component c3 = new Component("Holzplatte","Aus feinem Edelholz mit schn�rkel","Holz");
							
							Module m1 = new Module("Tischbein", c1, c2, false);
							Module m2 = new Module("Tisch", m1, m1, c3, true); //ALLES TEST
							
							Boolean endproductState = m2.getEndproductState();
							
							if (endproductState){
								
								endproductLabel.setText("Als Endprodukt gekennzeichnet");
								}
							
								else{
								
								endproductLabel.setText("Als kein Endprodukt gekennzeichnet.");
							}
							
							String elementResult = "";
							
							ArrayList<Component> component = new ArrayList<Component>();
							component = m2.getComponent();
							
							for(int i=0; i<component.size(); i++){
								Component com = component.get(i);
								elementResult = elementResult + com.getName() + "<br>";
							}
							
							elementTohtml.setHTML("<b>Bauteil(e):</b> <br>" + elementResult);
							
							String moduleResult = "";
							
							ArrayList<Module> module = new ArrayList<Module>();
							module = m2.getModule();
							
							for(int i=0; i<module.size(); i++){
								Module mod = module.get(i);
								moduleResult = moduleResult + mod.getName() + "<br>";
							}
							
							moduleTohtml.setHTML("<b>Baugruppe(n):</b> <br>" + moduleResult);
							
							modulePanel.setVisible(true);
							errorLabel.setVisible(false);
							
						}
						
						else if (result.equals("component")){
							
							Component element = new Component("Brett","Aus feinem Edelholz mit schn�rkel","Holz"); // Testzweck
							
							String conv = "<b>Beschreibung:</b> <br>" + element.getDescription() +"<br><br><b>Material:</b> <br>" + element.getMaterial();
							convTohtml.setHTML(conv);
							convTohtml.setVisible(true);
							errorLabel.setVisible(false);
						}
						else{
							System.out.println("Fehler im Asyn getTypeofContent");
						}
					
					}
		});
		
	}
		
	private void edit(){
		
	}
	
	private void delete(){
		
	}
	
	
	// ONLOAD ########################################################################################################

	
		public void onLoad() {
			
			editButton.setPixelSize(180, 30);
			deleteButton.setPixelSize(180, 30);
			
			buttonPanel.add(editButton);
			buttonPanel.add(deleteButton);
			
			modulePanel.add(moduleTohtml);
			modulePanel.add(elementTohtml);
			modulePanel.add(endproductLabel);
			
			mainPanel.add(nameLabel);
			mainPanel.add(modulePanel);
			mainPanel.add(convTohtml);
	
			
			dockPanel.add(searchResultLabel, DockPanel.NORTH); 		//North
			dockPanel.add(mainPanel, DockPanel.WEST); 			//West
			dockPanel.add(new HTML(" "), DockPanel.EAST); 		//East
			dockPanel.add(errorLabel, DockPanel.SOUTH); 		//South
			dockPanel.add(buttonPanel, DockPanel.NORTH); 		//Second North
			dockPanel.add(new HTML(" "), DockPanel.SOUTH); 		//Second South
			
			dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
			dockPanel.setStyleName("dockpanel");
			dockPanel.setSpacing(5);
			searchResultLabel.setStyleName("header");
			
			convTohtml.setVisible(false);
			modulePanel.setVisible(false);
			errorLabel.setVisible(false);
			
			if(result){
				
				show();
				
			}
			else{
				
				System.out.println("error");
				convTohtml.setVisible(false);
				buttonPanel.setVisible(false);
				errorLabel.setVisible(true);
				errorLabel.addStyleName("serverResponseLabelError");
			}
			
		    RootPanel.get("rightside").add(dockPanel);
		    
	// DIALOGBOX ########################################################################################################
		    
		    
		    /*dialogBox.setText("Komponente - Anlegen");
			dialogBox.setAnimationEnabled(true);
			
			dialogboxVPanel.add(new HTML("Folgende Komponente wurde erfolgreich angelegt:<br><br>"));
			dialogboxVPanel.add(serverResponseLabel);
			dialogboxVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogboxVPanel.add(closeButton);
			dialogBox.setWidget(dialogboxVPanel)*/
		 		
			
	// HANDLER ########################################################################################################
			
			
		 	/*deleteButton.addKeyUpHandler(new KeyUpHandler() {
				
				@Override
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
						{
							delete();
						}
					}
				});	*/	
					
		 	deleteButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {

					delete();

				}
			});
		 	
		 	editButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {

					edit();

				}
			});
			
		}

	}