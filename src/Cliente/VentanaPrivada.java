/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

//Importar librerias para encriptar
import AES.EncriptionException;
import AES.GenEncription;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author misa
 */
public class VentanaPrivada extends JFrame implements ActionListener {

    /**
     * Creates new form VentanaPrivada
     */
    Cliente cliente;
    String amigo;
    principal principal_;
    principal principal;
    GenEncription gen;
    public String llave_rondas = null;
    public VentanaPrivada(Cliente cliente, principal principal) {
        initComponents();
        this.cliente=cliente;
        this.principal=principal;
        panMostrarPriv.setEditable(false );
        txtMensajePriv.requestFocus();
        btnEnviar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        txtMensajePriv.addActionListener(this);
        amigo="";
        gen = new GenEncription();
        
        this.addWindowListener(new WindowListener()
        {         
         public void windowClosing(WindowEvent e) {
            cerrarVentana();
         }
         public void windowClosed(WindowEvent e) {}         
         public void windowOpened(WindowEvent e) {}
         public void windowIconified(WindowEvent e) {}
         public void windowDeiconified(WindowEvent e) {}
         public void windowActivated(WindowEvent e) {}
         public void windowDeactivated(WindowEvent e) {}
      });
        
    }
    
    public void setAmigo(String ami)
   {      
      this.amigo=ami;
      this.setTitle("Chateando con "+ ami);      
   }
    private void cerrarVentana() 
    { 
        this.setVisible(false);
        principal_.setVisible(true);
    }
    public void mostrarMsg(String msg)
     { 
          String[] msg_separado = msg.split(">");
          try {
                //desencriptando MSJ
                String msg_desencripta = gen.desencripta(principal.llave_rondas.substring(0, 32), msg_separado[1]);
                 this.panMostrarPriv.append(msg_separado[0]+">"+msg_desencripta+"\n");
                 this.bajarScroll();
            } catch (EncriptionException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
    public void mostrarMsgLocal(String msg){
        this.panMostrarPriv.append(msg+"\n");
    }
    
    public void bajarScroll(){
        this.panMostrarPriv.setCaretPosition(this.panMostrarPriv.getDocument().getLength());
    }
    
    public void setPK(String pk){
        this.llave_rondas = pk;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panMostrarPriv = new javax.swing.JTextArea();
        txtMensajePriv = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panMostrarPriv.setColumns(20);
        panMostrarPriv.setRows(5);
        jScrollPane1.setViewportView(panMostrarPriv);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
            .addComponent(txtMensajePriv)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnviar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMensajePriv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(btnLimpiar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea panMostrarPriv;
    private javax.swing.JTextField txtMensajePriv;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
         if(evt.getSource()==this.btnEnviar || evt.getSource()==this.txtMensajePriv){
             if(principal.llave_rondas == null){
                JOptionPane.showMessageDialog(null, "No existen mas usuarios para chatear");
                System.out.println("MSJ para "+amigo+" llave crifrado "+ principal.llave_rondas.substring(0, 32));
                txtMensajePriv.setText("");
             }else{
                 try{
                    String mensaje = txtMensajePriv.getText();
                    //Obtenemos Llave de cifrado
                    String key_s = principal.llave_rondas.substring(0, 32);
                    System.out.println("Llave cifrado PRIVADO " + key_s);
                    //Mostrams MSJ en nuestra propia ventana
                    mostrarMsgLocal(cliente.getNombre() + ">"+ mensaje);
                    try {
                        String msj_encripta = gen.encripta(key_s, mensaje);
                        //Enviamos MSJ encriptado
                        cliente.flujo(amigo, msj_encripta);
                    } catch (EncriptionException ex) {
                        Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Scroll
                    panMostrarPriv.setCaretPosition(panMostrarPriv.getDocument().getLength());
                    txtMensajePriv.setText("");
                }catch(Exception ei){
                    System.out.println("Error al enviar msj privado "+ ei);
                }
             }
        }else if(evt.getSource()==this.btnLimpiar){
                this.panMostrarPriv.setText("");
                txtMensajePriv.requestFocus();
        }
    }
 }
