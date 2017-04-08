#include <SPI.h>
#include <WiFi.h>

#define LED RED_LED
char ssid[] = "Sara Network";          //  your network SSID (name) 
char pass[] = "saras123";   // your network password

int status = WL_IDLE_STATUS;
IPAddress server(192,168,1,238);

// Initialize the client library
WiFiClient client;

void setup() { 
  Serial.begin(9600);
  Serial.println("Attempting to connect to WPA network...");
  Serial.print("SSID: ");
  Serial.println(ssid);

  while(true){
  status = WiFi.begin(ssid, pass);
  Serial.println(status);
  if ( status != WL_CONNECTED) { 
    Serial.println("Couldn't get a wifi connection");
  } 
  else {
    Serial.println("Connected to wifi");
    Serial.println("\nStarting connection...");
    // if you get a connection, report back via serial:
    while (true){
    if (client.connect(server, 5000)) {
        Serial.println("Connected");
        // Make a HTTP request:
        client.println("GET /");
        client.println();
        String c = client.readString();
        Serial.println(c);
        for (int i=0; i<c.length(); i++){
          
            if (i == 3){
              String state =  c.substring(i-3,i);
              
              String on = "ON ";
              String off = "OFF";
              if  (state.equals(on)){
                pinMode(4,OUTPUT);
                digitalWrite(4, HIGH);
              }

              
              else if (state.equals(off)) {
                pinMode(4,OUTPUT);
                digitalWrite(4, LOW);}
              else{
                Serial.println("Někde je asi chyba");
              }
            }
           if (i == 7){
              String state =  c.substring(i-3,i);
          
              String on = "ON ";
              String off = "OFF";
              if  (state.equals(on)){
                pinMode(6,OUTPUT);
                digitalWrite(6, HIGH);
              }

              
              else if (state.equals(off)) {
                pinMode(6,OUTPUT);
                digitalWrite(6, LOW);}
              else{
                Serial.println("Někde je asi chyba");
              }
            }
            
            
            if (i == 11){
              String state =  c.substring(i-3,i);
              
              String on = "ON ";
              String off = "OFF";
              if  (state.equals(on)){
                pinMode(8,OUTPUT);
                digitalWrite(8, HIGH);
              }

              
              else if (state.equals(off)) {
                pinMode(8,OUTPUT);
                digitalWrite(8, LOW);}
              else{
                Serial.println("Někde je asi chyba");
              }
            }
        if (i == 15){
         
              String state =  c.substring(i-3,i);
              String on = "ON ";
              String off = "OFF";
              if  (state.equals(on)){
                pinMode(9,OUTPUT);
                digitalWrite(9, HIGH);
              
              }
              

              
              else if (state.equals(off)) {
                pinMode(9,OUTPUT);
                digitalWrite(9, LOW);}
              else{
                Serial.println("Někde je asi chyba");
               
               
              }
              
            
            
          
         
          
        }
          
         
if (i == 19){
         
              String state =  c.substring(i-3,i);
              Serial.println(state);
              String on = "ON ";
              String off = "OFF";
              if  (state.equals(on)){
                pinMode(10,OUTPUT);
                digitalWrite(10, HIGH);
              
              }
              

              
              else if (state.equals(off)) {
                pinMode(10,OUTPUT);
                digitalWrite(10, LOW);}
              else{
                Serial.println("Někde je asi chyba");
               
               
              }
              
            
            
          
         
          
        }
        
        
        }
                  }}
      break;
      
    }
  }
}


void loop() {

}
