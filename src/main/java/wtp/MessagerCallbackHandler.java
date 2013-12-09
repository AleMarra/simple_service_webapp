
/**
 * MessagerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package wtp;

    /**
     *  MessagerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MessagerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MessagerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MessagerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for updateChat method
            * override this method for handling normal response from updateChat operation
            */
           public void receiveResultupdateChat(
                    wtp.MessagerStub.UpdateChatResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateChat operation
           */
            public void receiveErrorupdateChat(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMembers method
            * override this method for handling normal response from getMembers operation
            */
           public void receiveResultgetMembers(
                    wtp.MessagerStub.GetMembersResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMembers operation
           */
            public void receiveErrorgetMembers(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for logout method
            * override this method for handling normal response from logout operation
            */
           public void receiveResultlogout(
                    wtp.MessagerStub.LogoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from logout operation
           */
            public void receiveErrorlogout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getHistoryChat method
            * override this method for handling normal response from getHistoryChat operation
            */
           public void receiveResultgetHistoryChat(
                    wtp.MessagerStub.GetHistoryChatResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHistoryChat operation
           */
            public void receiveErrorgetHistoryChat(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createChat method
            * override this method for handling normal response from createChat operation
            */
           public void receiveResultcreateChat(
                    wtp.MessagerStub.CreateChatResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createChat operation
           */
            public void receiveErrorcreateChat(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sendMessage method
            * override this method for handling normal response from sendMessage operation
            */
           public void receiveResultsendMessage(
                    wtp.MessagerStub.SendMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendMessage operation
           */
            public void receiveErrorsendMessage(java.lang.Exception e) {
            }
                


    }
    