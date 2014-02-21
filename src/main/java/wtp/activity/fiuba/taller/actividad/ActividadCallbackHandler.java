
/**
 * ActividadCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package wtp.activity.fiuba.taller.actividad;

    /**
     *  ActividadCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ActividadCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ActividadCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ActividadCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for setPropiedades method
            * override this method for handling normal response from setPropiedades operation
            */
           public void receiveResultsetPropiedades(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setPropiedades operation
           */
            public void receiveErrorsetPropiedades(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getGrupos method
            * override this method for handling normal response from getGrupos operation
            */
           public void receiveResultgetGrupos(
                    ActividadStub.GetGruposResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getGrupos operation
           */
            public void receiveErrorgetGrupos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarParticipanteAGrupo method
            * override this method for handling normal response from agregarParticipanteAGrupo operation
            */
           public void receiveResultagregarParticipanteAGrupo(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarParticipanteAGrupo operation
           */
            public void receiveErroragregarParticipanteAGrupo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getActividadesDeAmbito method
            * override this method for handling normal response from getActividadesDeAmbito operation
            */
           public void receiveResultgetActividadesDeAmbito(
                    ActividadStub.GetActividadesDeAmbitoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getActividadesDeAmbito operation
           */
            public void receiveErrorgetActividadesDeAmbito(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearActividadIndividualEvaluable method
            * override this method for handling normal response from crearActividadIndividualEvaluable operation
            */
           public void receiveResultcrearActividadIndividualEvaluable(
                    ActividadStub.CrearActividadIndividualEvaluableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearActividadIndividualEvaluable operation
           */
            public void receiveErrorcrearActividadIndividualEvaluable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPropiedades method
            * override this method for handling normal response from getPropiedades operation
            */
           public void receiveResultgetPropiedades(
                    ActividadStub.GetPropiedadesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPropiedades operation
           */
            public void receiveErrorgetPropiedades(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarParticipante method
            * override this method for handling normal response from eliminarParticipante operation
            */
           public void receiveResulteliminarParticipante(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarParticipante operation
           */
            public void receiveErroreliminarParticipante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearActividadGrupalEvaluable method
            * override this method for handling normal response from crearActividadGrupalEvaluable operation
            */
           public void receiveResultcrearActividadGrupalEvaluable(
                    ActividadStub.CrearActividadGrupalEvaluableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearActividadGrupalEvaluable operation
           */
            public void receiveErrorcrearActividadGrupalEvaluable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for destruirActividad method
            * override this method for handling normal response from destruirActividad operation
            */
           public void receiveResultdestruirActividad(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from destruirActividad operation
           */
            public void receiveErrordestruirActividad(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getNotas method
            * override this method for handling normal response from getNotas operation
            */
           public void receiveResultgetNotas(
                    ActividadStub.GetNotasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getNotas operation
           */
            public void receiveErrorgetNotas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getActividadesDeActividad method
            * override this method for handling normal response from getActividadesDeActividad operation
            */
           public void receiveResultgetActividadesDeActividad(
                    ActividadStub.GetActividadesDeActividadResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getActividadesDeActividad operation
           */
            public void receiveErrorgetActividadesDeActividad(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getActividadesDeMiembro method
            * override this method for handling normal response from getActividadesDeMiembro operation
            */
           public void receiveResultgetActividadesDeMiembro(
                    ActividadStub.GetActividadesDeMiembroResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getActividadesDeMiembro operation
           */
            public void receiveErrorgetActividadesDeMiembro(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearActividadGrupal method
            * override this method for handling normal response from crearActividadGrupal operation
            */
           public void receiveResultcrearActividadGrupal(
                    ActividadStub.CrearActividadGrupalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearActividadGrupal operation
           */
            public void receiveErrorcrearActividadGrupal(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarParticipanteAGrupo method
            * override this method for handling normal response from eliminarParticipanteAGrupo operation
            */
           public void receiveResulteliminarParticipanteAGrupo(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarParticipanteAGrupo operation
           */
            public void receiveErroreliminarParticipanteAGrupo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for evaluar method
            * override this method for handling normal response from evaluar operation
            */
           public void receiveResultevaluar(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from evaluar operation
           */
            public void receiveErrorevaluar(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getParticipantes method
            * override this method for handling normal response from getParticipantes operation
            */
           public void receiveResultgetParticipantes(
                    ActividadStub.GetParticipantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getParticipantes operation
           */
            public void receiveErrorgetParticipantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for crearActividadIndividual method
            * override this method for handling normal response from crearActividadIndividual operation
            */
           public void receiveResultcrearActividadIndividual(
                    ActividadStub.CrearActividadIndividualResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from crearActividadIndividual operation
           */
            public void receiveErrorcrearActividadIndividual(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarParticipante method
            * override this method for handling normal response from agregarParticipante operation
            */
           public void receiveResultagregarParticipante(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarParticipante operation
           */
            public void receiveErroragregarParticipante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregarGrupo method
            * override this method for handling normal response from agregarGrupo operation
            */
           public void receiveResultagregarGrupo(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregarGrupo operation
           */
            public void receiveErroragregarGrupo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getNota method
            * override this method for handling normal response from getNota operation
            */
           public void receiveResultgetNota(
                    ActividadStub.GetNotaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getNota operation
           */
            public void receiveErrorgetNota(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarGrupo method
            * override this method for handling normal response from eliminarGrupo operation
            */
           public void receiveResulteliminarGrupo(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarGrupo operation
           */
            public void receiveErroreliminarGrupo(java.lang.Exception e) {
            }
                


    }
    