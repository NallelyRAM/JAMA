/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import dominio.Paciente;
import dominio.Persona;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class agregarPacienteP2 extends javax.swing.JFrame {

    static Paciente paciente;
    static Persona persona;

    /**
     * Creates new form agregarPacienteP2
     *
     * @param myPaciente
     * @param persona
     */
    public agregarPacienteP2(Paciente myPaciente, Persona persona) {
        initComponents();
        this.paciente = myPaciente;
        this.persona = persona;
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static Paciente getPaciente() {
        if (paciente != null) {
            return paciente;
        }
        return new Paciente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtTalla = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaConsulta = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        btnFinalzar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(867, 618));

        jbnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/atras1.PNG"))); // NOI18N
        jbnAtras.setToolTipText("");
        jbnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbnAtrasMouseClicked(evt);
            }
        });
        jbnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnAtrasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel1.setText("Talla(CM):");

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel2.setText("Peso(KG):");

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel3.setText("Motivo de la consulta:");

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel4.setText("Altura(CM):");

        txtPeso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPeso.setCaretColor(new java.awt.Color(153, 255, 153));
        txtPeso.setDisabledTextColor(new java.awt.Color(153, 255, 153));
        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });

        txtTalla.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTallaActionPerformed(evt);
            }
        });

        txtAltura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtAreaConsulta.setColumns(20);
        txtAreaConsulta.setRows(5);
        jScrollPane1.setViewportView(txtAreaConsulta);

        btnLimpiar.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnFinalzar.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        btnFinalzar.setText("Finalizar");
        btnFinalzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(226, 226, 226)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4))
                            .addGap(42, 42, 42)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(27, 27, 27))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnLimpiar)
                            .addGap(117, 117, 117)
                            .addComponent(btnFinalzar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jbnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jbnAtras)
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalzar)
                    .addComponent(btnLimpiar))
                .addGap(16, 16, 16))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 153));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/logochico.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(Logo)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(Logo)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void jbnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnAtrasActionPerformed
        this.dispose();
        try {
            agregarPacienteP1 agregarPaciente = new agregarPacienteP1();
        } catch (SQLException ex) {
            Logger.getLogger(FrInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbnAtrasActionPerformed

    private void jbnAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbnAtrasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbnAtrasMouseClicked

    private void btnFinalzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalzarActionPerformed

        float talla=0, estatura=0, peso = 0;
        try {
            if (validacion()) {
                if (Float.parseFloat(txtTalla.getText())%2!=0) {
                    JOptionPane.showMessageDialog(null, "Error al registar la talla del paciente");
                }
                else talla = Float.parseFloat(txtTalla.getText());
                
                if (Float.parseFloat(txtAltura.getText())%2!=0) {
                    JOptionPane.showMessageDialog(null, "Error al registar la estatura del paciente");
                }
                else estatura = Float.parseFloat(txtAltura.getText());
                
                if (Float.parseFloat(txtPeso.getText())%2!=0) {
                    JOptionPane.showMessageDialog(null, "Error al registar el peso del paciente");
                }
                else peso = Float.parseFloat(txtPeso.getText());
                
                String motivoConsulta = txtAreaConsulta.getText();

                paciente = new Paciente(motivoConsulta, null, peso, estatura, talla);

                agregarPacienteP1.guardarCambios(paciente, persona);
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar los datos, llenelos e intentelo de nuevo.");
            System.out.println(e);
            btnLimpiar();
        }


    }//GEN-LAST:event_btnFinalzarActionPerformed

    private void txtTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        btnLimpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiar() {
        this.txtPeso.setText("");
        this.txtAltura.setText("");
        this.txtTalla.setText("");
        this.txtAreaConsulta.setText("");
    }

    private boolean validacion() {
        if (txtPeso.getText().equals("")
                || txtAltura.getText().equals("")
                || txtTalla.getText().equals("")
                || txtAreaConsulta.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Campos vacíos, llenelos e intentelo de nuevo");
            return false;
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JButton btnFinalzar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbnAtras;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextArea txtAreaConsulta;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTalla;
    // End of variables declaration//GEN-END:variables
}
