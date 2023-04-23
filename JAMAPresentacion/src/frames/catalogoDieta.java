/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import constantes.Constantes;
import dominio.Dieta;
import dominio.Paciente;
import dominio.Platillo;
import interfaces.IPersistenciaFachada;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import negocio.PersistenciaFachada;

/**
 *
 * @author Usuario
 */
public class CatalogoDieta extends javax.swing.JFrame {

    IPersistenciaFachada persistenciaFachada;
    static final int INDEX_DEFAULT = 0;
    int seleccionDesayuno = -1;
    int seleccionComida = -1;
    int seleccionCena = -1;
    ArrayList<Dieta> dietas;

    int indexDesayuno = 0;
    int indexComida = 0;
    int indexCena = 0;

    int desayunosCatalogo = 3;
    int comidasCatalogo = 3;
    int cenasCatalogo = 3;

    Paciente paciente;
    int operacion;

    /**
     * Creates new form catalogoDieta
     */
    public CatalogoDieta(Paciente paciente, int operacion) {
        initComponents();
        this.operacion = operacion;
        setSize(907, 900);
        setLocationRelativeTo(null);
        setResizable(false);

        this.paciente = paciente;
        persistenciaFachada = PersistenciaFachada.getInstance();

        dietas = persistenciaFachada.buscarDietas();
        llenarCatalogo(dietas, INDEX_DEFAULT, "todos");
    }

    private void llenarCatalogo(ArrayList<Dieta> dietas, int index, String seleccion) {
        JLabel[] nombreLabelsDesayuno = {lblNombreDesayuno1, lblNombreDesayuno2, lblNombreDesayuno3};
        JLabel[] nombreLabelsComida = {lblNombreComida1, lblNombreComida2, lblNombreComida3};
        JLabel[] nombreLabelsCena = {lblNombreCena1, lblNombreCena2, lblNombreCena3};

        JLabel[] imagenLabelsDesayuno = {lblImagenDesayuno1, lblImagenDesayuno2, lblImagenDesayuno3};
        JLabel[] imagenLabelsComida = {lblImagenComida1, lblImagenComidaDos, lblImagenComida3};
        JLabel[] imagenLabelsCena = {lblImagenCena1, lblImagenCena2, lblImagenCena3};

        int indexLabels = 0;

        for (int i = index; i < dietas.size(); i++) {

            try {

                switch (seleccion) {
                    case "todos":
                        if (dietas.get(i).getDesayuno() != null) {
                            nombreLabelsDesayuno[indexLabels].setText(dietas.get(i).getDesayuno().getNombre());
                            escalarImagen(dietas.get(i).getDesayuno().getFoto(), imagenLabelsDesayuno[indexLabels]);
                        }

                        // Cargar Comidas ...
                        if (dietas.get(i).getComida() != null) {
                            nombreLabelsComida[indexLabels].setText(dietas.get(i).getComida().getNombre());
                            escalarImagen(dietas.get(i).getComida().getFoto(), imagenLabelsComida[indexLabels]);
                        }

                        // Cargar Cenas ...
                        if (dietas.get(i).getCena() != null) {
                            nombreLabelsCena[indexLabels].setText(dietas.get(i).getCena().getNombre());
                            escalarImagen(dietas.get(i).getCena().getFoto(), imagenLabelsCena[indexLabels]);
                        }

                        break;

                    case "desayuno":
                        if (dietas.get(i).getDesayuno() != null) {
                            nombreLabelsDesayuno[indexLabels].setText(dietas.get(i).getDesayuno().getNombre());
                            escalarImagen(dietas.get(i).getDesayuno().getFoto(), imagenLabelsDesayuno[indexLabels]);
                            break;
                        }
                    case "comida":
                        // Cargar Comidas ...
                        if (dietas.get(i).getComida() != null) {
                            nombreLabelsComida[indexLabels].setText(dietas.get(i).getComida().getNombre());
                            escalarImagen(dietas.get(i).getComida().getFoto(), imagenLabelsComida[indexLabels]);
                            break;
                        }
                    case "cena":
                        if (dietas.get(i).getCena() != null) {
                            nombreLabelsCena[indexLabels].setText(dietas.get(i).getCena().getNombre());
                            escalarImagen(dietas.get(i).getCena().getFoto(), imagenLabelsCena[indexLabels]);
                        }
                }
                indexLabels++;

            } catch (IndexOutOfBoundsException e) {

            }

        }

        escalarLabels(nombreLabelsDesayuno);
        escalarLabels(nombreLabelsComida);
        escalarLabels(nombreLabelsCena);

    }

    private void escalarLabels(JLabel labels[]) {
        for (int i = 0; i < labels.length; i++) {
            Font font = labels[i].getFont();
            FontMetrics metrics = labels[i].getFontMetrics(font);

            while (metrics.stringWidth(labels[i].getText()) > labels[i].getWidth()) {
                font = font.deriveFont((float) font.getSize() - 1);
                metrics = labels[i].getFontMetrics(font);
            }
            labels[i].setFont(font);
        }

    }

    public void escalarImagen(byte[] imagenBytes, JLabel label) {

        BufferedImage image = null;
        File tempFile = null;

        try {
            tempFile = File.createTempFile("temp", ".jpg");
        } catch (IOException ex) {
            System.out.println("Error en: " + ex);
        }
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(imagenBytes);
        } catch (Exception ex) {
            System.out.println("error: " + ex);
        }

        try {
            image = ImageIO.read(tempFile);
        } catch (IOException ex) {
            Logger.getLogger(DietaDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        label.setIcon(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        JbGuardar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblDesayunos = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblArribaDesayuno = new javax.swing.JLabel();
        lblAbajoDesayuno = new javax.swing.JLabel();
        myPanelDesayuno1 = new javax.swing.JPanel();
        lblImagenDesayuno1 = new javax.swing.JLabel();
        lblNombreDesayuno1 = new javax.swing.JLabel();
        myPanelDesayuno3 = new javax.swing.JPanel();
        lblImagenDesayuno2 = new javax.swing.JLabel();
        lblNombreDesayuno2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        myPanelDesayuno4 = new javax.swing.JPanel();
        lblImagenDesayuno3 = new javax.swing.JLabel();
        lblNombreDesayuno3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblComidas = new javax.swing.JLabel();
        lblArribaComida = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        myPanelComida2 = new javax.swing.JPanel();
        lblImagenComidaDos = new javax.swing.JLabel();
        lblNombreComida2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        myPanelComida1 = new javax.swing.JPanel();
        lblImagenComida1 = new javax.swing.JLabel();
        lblNombreComida1 = new javax.swing.JLabel();
        myPanelComida3 = new javax.swing.JPanel();
        lblImagenComida3 = new javax.swing.JLabel();
        lblNombreComida3 = new javax.swing.JLabel();
        lblAbajoComida = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        myPanelCena1 = new javax.swing.JPanel();
        lblImagenCena1 = new javax.swing.JLabel();
        lblNombreCena1 = new javax.swing.JLabel();
        myPanelCena3 = new javax.swing.JPanel();
        lblImagenCena3 = new javax.swing.JLabel();
        lblNombreCena3 = new javax.swing.JLabel();
        lblAbajoCena = new javax.swing.JLabel();
        lblCenas = new javax.swing.JLabel();
        lblArribaCena = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        myPanelCena2 = new javax.swing.JPanel();
        lblImagenCena2 = new javax.swing.JLabel();
        lblNombreCena2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Catalogo de Dietas");
        getContentPane().setLayout(null);

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/LogoC_SinFondo.png"))); // NOI18N

        JbGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JbGuardar.setText("Guardar");
        JbGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(JbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(376, 376, 376))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(JbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(Logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 1228, 98);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblDesayunos.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblDesayunos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDesayunos.setText("Desayunos");

        lblArribaDesayuno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arriba.PNG"))); // NOI18N
        lblArribaDesayuno.setText("jLabel4");
        lblArribaDesayuno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblArribaDesayuno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblArribaDesayunoMousePressed(evt);
            }
        });

        lblAbajoDesayuno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/abajo.PNG"))); // NOI18N
        lblAbajoDesayuno.setText("jLabel5");
        lblAbajoDesayuno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAbajoDesayuno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAbajoDesayunoMousePressed(evt);
            }
        });

        myPanelDesayuno1.setBackground(new java.awt.Color(204, 204, 204));
        myPanelDesayuno1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelDesayuno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelDesayuno1MousePressed(evt);
            }
        });

        lblImagenDesayuno1.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreDesayuno1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreDesayuno1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreDesayuno1.setText("  ");

        javax.swing.GroupLayout myPanelDesayuno1Layout = new javax.swing.GroupLayout(myPanelDesayuno1);
        myPanelDesayuno1.setLayout(myPanelDesayuno1Layout);
        myPanelDesayuno1Layout.setHorizontalGroup(
            myPanelDesayuno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreDesayuno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblImagenDesayuno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelDesayuno1Layout.setVerticalGroup(
            myPanelDesayuno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelDesayuno1Layout.createSequentialGroup()
                .addComponent(lblNombreDesayuno1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenDesayuno1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        myPanelDesayuno3.setBackground(new java.awt.Color(204, 204, 204));
        myPanelDesayuno3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelDesayuno3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelDesayuno3MousePressed(evt);
            }
        });

        lblImagenDesayuno2.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreDesayuno2.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreDesayuno2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreDesayuno2.setText(" ");

        javax.swing.GroupLayout myPanelDesayuno3Layout = new javax.swing.GroupLayout(myPanelDesayuno3);
        myPanelDesayuno3.setLayout(myPanelDesayuno3Layout);
        myPanelDesayuno3Layout.setHorizontalGroup(
            myPanelDesayuno3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreDesayuno2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblImagenDesayuno2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelDesayuno3Layout.setVerticalGroup(
            myPanelDesayuno3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelDesayuno3Layout.createSequentialGroup()
                .addComponent(lblNombreDesayuno2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenDesayuno2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        myPanelDesayuno4.setBackground(new java.awt.Color(204, 204, 204));
        myPanelDesayuno4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelDesayuno4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelDesayuno4MousePressed(evt);
            }
        });

        lblImagenDesayuno3.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreDesayuno3.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreDesayuno3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreDesayuno3.setText("   ");

        javax.swing.GroupLayout myPanelDesayuno4Layout = new javax.swing.GroupLayout(myPanelDesayuno4);
        myPanelDesayuno4.setLayout(myPanelDesayuno4Layout);
        myPanelDesayuno4Layout.setHorizontalGroup(
            myPanelDesayuno4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreDesayuno3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addComponent(lblImagenDesayuno3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelDesayuno4Layout.setVerticalGroup(
            myPanelDesayuno4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelDesayuno4Layout.createSequentialGroup()
                .addComponent(lblNombreDesayuno3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenDesayuno3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblComidas.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblComidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComidas.setText("Comidas");

        lblArribaComida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arriba.PNG"))); // NOI18N
        lblArribaComida.setText("jLabel4");
        lblArribaComida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblArribaComida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblArribaComidaMousePressed(evt);
            }
        });

        myPanelComida2.setBackground(new java.awt.Color(204, 204, 204));
        myPanelComida2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelComida2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelComida2MousePressed(evt);
            }
        });

        lblImagenComidaDos.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreComida2.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreComida2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreComida2.setText(" ");

        javax.swing.GroupLayout myPanelComida2Layout = new javax.swing.GroupLayout(myPanelComida2);
        myPanelComida2.setLayout(myPanelComida2Layout);
        myPanelComida2Layout.setHorizontalGroup(
            myPanelComida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreComida2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblImagenComidaDos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelComida2Layout.setVerticalGroup(
            myPanelComida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelComida2Layout.createSequentialGroup()
                .addComponent(lblNombreComida2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenComidaDos, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        myPanelComida1.setBackground(new java.awt.Color(204, 204, 204));
        myPanelComida1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelComida1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelComida1MousePressed(evt);
            }
        });

        lblImagenComida1.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreComida1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreComida1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreComida1.setText("  ");

        javax.swing.GroupLayout myPanelComida1Layout = new javax.swing.GroupLayout(myPanelComida1);
        myPanelComida1.setLayout(myPanelComida1Layout);
        myPanelComida1Layout.setHorizontalGroup(
            myPanelComida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreComida1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
            .addComponent(lblImagenComida1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelComida1Layout.setVerticalGroup(
            myPanelComida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelComida1Layout.createSequentialGroup()
                .addComponent(lblNombreComida1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenComida1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        myPanelComida3.setBackground(new java.awt.Color(204, 204, 204));
        myPanelComida3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelComida3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelComida3MousePressed(evt);
            }
        });

        lblImagenComida3.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreComida3.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreComida3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreComida3.setText(" ");

        javax.swing.GroupLayout myPanelComida3Layout = new javax.swing.GroupLayout(myPanelComida3);
        myPanelComida3.setLayout(myPanelComida3Layout);
        myPanelComida3Layout.setHorizontalGroup(
            myPanelComida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreComida3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblImagenComida3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelComida3Layout.setVerticalGroup(
            myPanelComida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelComida3Layout.createSequentialGroup()
                .addComponent(lblNombreComida3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenComida3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblAbajoComida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/abajo.PNG"))); // NOI18N
        lblAbajoComida.setText("jLabel5");
        lblAbajoComida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAbajoComida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAbajoComidaMousePressed(evt);
            }
        });

        myPanelCena1.setBackground(new java.awt.Color(204, 204, 204));
        myPanelCena1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelCena1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelCena1MousePressed(evt);
            }
        });

        lblImagenCena1.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreCena1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreCena1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCena1.setText("  ");

        javax.swing.GroupLayout myPanelCena1Layout = new javax.swing.GroupLayout(myPanelCena1);
        myPanelCena1.setLayout(myPanelCena1Layout);
        myPanelCena1Layout.setHorizontalGroup(
            myPanelCena1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreCena1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
            .addComponent(lblImagenCena1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelCena1Layout.setVerticalGroup(
            myPanelCena1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelCena1Layout.createSequentialGroup()
                .addComponent(lblNombreCena1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenCena1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        myPanelCena3.setBackground(new java.awt.Color(204, 204, 204));
        myPanelCena3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelCena3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelCena3MousePressed(evt);
            }
        });

        lblImagenCena3.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreCena3.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreCena3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCena3.setText(" ");

        javax.swing.GroupLayout myPanelCena3Layout = new javax.swing.GroupLayout(myPanelCena3);
        myPanelCena3.setLayout(myPanelCena3Layout);
        myPanelCena3Layout.setHorizontalGroup(
            myPanelCena3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreCena3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblImagenCena3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelCena3Layout.setVerticalGroup(
            myPanelCena3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelCena3Layout.createSequentialGroup()
                .addComponent(lblNombreCena3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenCena3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblAbajoCena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/abajo.PNG"))); // NOI18N
        lblAbajoCena.setText("jLabel5");
        lblAbajoCena.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAbajoCena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAbajoCenaMousePressed(evt);
            }
        });

        lblCenas.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblCenas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCenas.setText("Cenas");

        lblArribaCena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/arriba.PNG"))); // NOI18N
        lblArribaCena.setText("jLabel4");
        lblArribaCena.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblArribaCena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblArribaCenaMousePressed(evt);
            }
        });

        myPanelCena2.setBackground(new java.awt.Color(204, 204, 204));
        myPanelCena2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        myPanelCena2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myPanelCena2MousePressed(evt);
            }
        });

        lblImagenCena2.setBackground(new java.awt.Color(0, 0, 0));

        lblNombreCena2.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        lblNombreCena2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCena2.setText("  ");

        javax.swing.GroupLayout myPanelCena2Layout = new javax.swing.GroupLayout(myPanelCena2);
        myPanelCena2.setLayout(myPanelCena2Layout);
        myPanelCena2Layout.setHorizontalGroup(
            myPanelCena2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreCena2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblImagenCena2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myPanelCena2Layout.setVerticalGroup(
            myPanelCena2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPanelCena2Layout.createSequentialGroup()
                .addComponent(lblNombreCena2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagenCena2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblDesayunos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(myPanelDesayuno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator1)
                                        .addComponent(myPanelDesayuno3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(myPanelDesayuno4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(lblAbajoDesayuno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator6)
                                    .addComponent(myPanelComida3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator2)
                                    .addComponent(jSeparator5)
                                    .addComponent(myPanelComida2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblComidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(myPanelComida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAbajoComida, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(lblArribaDesayuno, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(256, 256, 256)
                        .addComponent(lblArribaComida, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9)
                    .addComponent(myPanelCena3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator7)
                    .addComponent(myPanelCena2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCenas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAbajoCena, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addComponent(myPanelCena1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(lblArribaCena, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(608, 608, 608))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCenas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblArribaCena)
                        .addGap(18, 18, 18)
                        .addComponent(myPanelCena1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myPanelCena2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myPanelCena3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAbajoCena))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDesayunos)
                            .addComponent(lblComidas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblArribaDesayuno)
                            .addComponent(lblArribaComida))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(myPanelDesayuno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(myPanelDesayuno3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(myPanelComida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(myPanelComida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(myPanelComida3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAbajoDesayuno)
                                    .addComponent(lblAbajoComida)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(myPanelDesayuno4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 96, 1380, 760);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionPlatillo(JPanel panel, int operacion) {
        // Colores ...
        Color grisClaro = new Color(204, 204, 204);
        Color grisFuerte = new Color(153, 153, 153);

        resetearSeleccion(operacion);

        panel.setBackground(panel.getBackground().equals(grisClaro)
                ? grisFuerte
                : grisClaro);

//        if (seleccionDesayuno != -1 && seleccionComida != -1 && seleccionCena != -1) {
//            System.out.println("Desayuno Seleccionado: " + dietas.get(seleccionDesayuno).getDesayuno().getNombre());
//            System.out.println("Comida Seleccionada: " + dietas.get(seleccionComida).getComida().getNombre());
//            System.out.println("Cena Seleccionada: " + dietas.get(seleccionCena).getCena().getNombre());
//            System.out.println("");
//            System.out.println("");
//        }
    }

    private void resetearSeleccion(int operacion) {
        // Colores ...
        Color grisClaro = new Color(204, 204, 204);

        switch (operacion) {
            // Caso que haya seleccionado desayunos.
            case 0:
                myPanelDesayuno1.setBackground(grisClaro);
                myPanelDesayuno3.setBackground(grisClaro);
                myPanelDesayuno4.setBackground(grisClaro);
                break;
            case 1:
                myPanelComida1.setBackground(grisClaro);
                myPanelComida2.setBackground(grisClaro);
                myPanelComida3.setBackground(grisClaro);
                break;
            case 2:
                myPanelCena1.setBackground(grisClaro);
                myPanelCena2.setBackground(grisClaro);
                myPanelCena3.setBackground(grisClaro);
                break;
        }
    }

    private void myPanelComida1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelComida1MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelComida1, evt, lblNombreComida1, "comida");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreComida1.getText().equals(dietas.get(i).getComida().getNombre())) {
                    seleccionComida = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelComida1, 1);
        }


    }//GEN-LAST:event_myPanelComida1MousePressed

    private void myPanelComida2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelComida2MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelComida2, evt, lblNombreComida2, "comida");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreComida2.getText().equals(dietas.get(i).getComida().getNombre())) {
                    seleccionComida = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelComida2, 1);
        }

    }//GEN-LAST:event_myPanelComida2MousePressed

    private void myPanelComida3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelComida3MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelComida3, evt, lblNombreComida3, "comida");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreComida3.getText().equals(dietas.get(i).getComida().getNombre())) {
                    seleccionComida = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelComida3, 1);
        }

    }//GEN-LAST:event_myPanelComida3MousePressed

    private void myPanelDesayuno1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelDesayuno1MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelDesayuno1, evt, lblNombreDesayuno1, "desayuno");
        } else {
            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreDesayuno1.getText().equals(dietas.get(i).getDesayuno().getNombre())) {
                    seleccionDesayuno = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelDesayuno1, 0);
        }


    }//GEN-LAST:event_myPanelDesayuno1MousePressed

    private void myPanelDesayuno3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelDesayuno3MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelDesayuno3, evt, lblNombreDesayuno2, "desayuno");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreDesayuno2.getText().equals(dietas.get(i).getDesayuno().getNombre())) {
                    seleccionDesayuno = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelDesayuno3, 0);
        }
    }//GEN-LAST:event_myPanelDesayuno3MousePressed

    private void myPanelDesayuno4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelDesayuno4MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelDesayuno4, evt, lblNombreDesayuno3, "desayuno");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreDesayuno3.getText().equals(dietas.get(i).getDesayuno().getNombre())) {
                    seleccionDesayuno = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelDesayuno4, 0);
        }


    }//GEN-LAST:event_myPanelDesayuno4MousePressed

    private void myPanelCena1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelCena1MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelCena1, evt, lblNombreCena1, "cena");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreCena1.getText().equals(dietas.get(i).getCena().getNombre())) {
                    seleccionCena = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelCena1, 2);
        }

    }//GEN-LAST:event_myPanelCena1MousePressed

    private void myPanelCena3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelCena3MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelCena3, evt, lblNombreCena3, "cena");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreCena3.getText().equals(dietas.get(i).getCena().getNombre())) {
                    seleccionCena = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelCena3, 2);
        }
    }//GEN-LAST:event_myPanelCena3MousePressed

    private void myPanelCena2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPanelCena2MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            crearMenu(myPanelCena2, evt, lblNombreCena2, "cena");
        } else {

            for (int i = 0; i < dietas.size(); i++) {
                if (lblNombreCena2.getText().equals(dietas.get(i).getCena().getNombre())) {
                    seleccionCena = i;
                    break;
                }
            }
            seleccionPlatillo(myPanelCena2, 2);
        }
    }//GEN-LAST:event_myPanelCena2MousePressed

    private void lblArribaDesayunoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArribaDesayunoMousePressed
        if (indexDesayuno > 0) {
            indexDesayuno--;
            desayunosCatalogo--;
            llenarCatalogo(dietas, indexDesayuno, "desayuno");
            resetearSeleccion(0);
            seleccionDesayuno = -1;
        }
    }//GEN-LAST:event_lblArribaDesayunoMousePressed

    private void lblAbajoDesayunoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAbajoDesayunoMousePressed

        if (desayunosCatalogo < dietas.size()) {
            desayunosCatalogo++;
            indexDesayuno++;
            llenarCatalogo(dietas, indexDesayuno, "desayuno");
            resetearSeleccion(0);
            seleccionDesayuno = -1;
        }
    }//GEN-LAST:event_lblAbajoDesayunoMousePressed

    private void lblArribaComidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArribaComidaMousePressed
        if (indexComida > 0) {
            indexComida--;
            comidasCatalogo--;
            llenarCatalogo(dietas, indexComida, "comida");
            resetearSeleccion(1);
            seleccionComida = -1;
        }
    }//GEN-LAST:event_lblArribaComidaMousePressed

    private void lblAbajoComidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAbajoComidaMousePressed
        if (comidasCatalogo < dietas.size()) {
            comidasCatalogo++;
            indexComida++;
            llenarCatalogo(dietas, indexComida, "comida");
            resetearSeleccion(1);
            seleccionComida = -1;
        }
    }//GEN-LAST:event_lblAbajoComidaMousePressed

    private void lblArribaCenaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArribaCenaMousePressed
        if (indexCena > 0) {
            indexCena--;
            cenasCatalogo--;
            llenarCatalogo(dietas, indexCena, "cena");
            resetearSeleccion(2);
            seleccionCena = -1;
        }
    }//GEN-LAST:event_lblArribaCenaMousePressed

    private void lblAbajoCenaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAbajoCenaMousePressed
        if (cenasCatalogo < dietas.size()) {
            indexCena++;
            cenasCatalogo++;
            llenarCatalogo(dietas, indexCena, "cena");
            resetearSeleccion(2);
            seleccionCena = -1;
        }
    }//GEN-LAST:event_lblAbajoCenaMousePressed

    private void JbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbGuardarActionPerformed
         String nombreDieta = "";
        int idDieta = 0;
        for (int i = 0; i < dietas.size(); i++) {
            Dieta myDieta = dietas.get(i);
            if (myDieta.getDesayuno() == dietas.get(seleccionDesayuno).getDesayuno()
                    && myDieta.getComida() == dietas.get(seleccionComida).getComida()
                    && myDieta.getCena() == dietas.get(seleccionCena).getCena()) {
                if (operacion == Constantes.AGREGAR) {
                    String mensaje = "Ya existe una dieta igual y es '" + myDieta.getNombreDieta() + "'";
                    JOptionPane.showMessageDialog(null, mensaje, "Dietas",
                            JOptionPane.INFORMATION_MESSAGE, null);
                    
                }
                idDieta = dietas.get(i).getIdDieta();
                nombreDieta = myDieta.getNombreDieta();
            } 
        }

        if (operacion == Constantes.AGREGAR) {
            nombreDieta = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre de la Dieta",
                    "Catalogo de Dietas", JOptionPane.INFORMATION_MESSAGE, Logo.getIcon(),
                    null, null);
        }

        Dieta dieta = new Dieta(idDieta, nombreDieta, new Date(), Date.from(LocalDate.now().
                plusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), 0,
                dietas.get(seleccionDesayuno).getDesayuno(), dietas.get(seleccionComida).getComida(),
                dietas.get(seleccionCena).getCena());

        if (operacion == Constantes.ACTUALIZAR) {
            if (paciente.getNombre() != null) {
                paciente.setDieta(dieta);
                JOptionPane.showMessageDialog(null, "Se agregó la dieta al paciente.", "Dietas",
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        }

        if (operacion == Constantes.AGREGAR) {
            if (persistenciaFachada.registrarDieta(dieta)) {
                JOptionPane.showMessageDialog(null, "Dieta guardada con éxito.", "Dietas",
                        JOptionPane.INFORMATION_MESSAGE, null);
                dietas = persistenciaFachada.buscarDietas();
                llenarCatalogo(dietas, INDEX_DEFAULT, "todos");
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error", "Dietas",
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
        
        if(operacion == Constantes.ACTUALIZAR){
            dispose();
        }
    }//GEN-LAST:event_JbGuardarActionPerformed

    private void abrirDialogInformacion(JMenuItem menuItem, Platillo platillo) {
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ingredientes = "--------------------------------\nIngredientes:\n" + platillo.getIngredientes();
                String acompañantes = "--------------------------------\nAcompañantes:\n" + platillo.getAcompanante();
                String numCalorias = "\nNúmero de Calorías:\n" + String.valueOf(platillo.getNumCalorias() + "\n--------------------------------");

                String platillo = ingredientes + "\n" + acompañantes + "\n" + numCalorias;
                JOptionPane.showMessageDialog(null, platillo, "Información del Platillo",
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        });
    }

    private void crearMenu(JPanel panel, MouseEvent evt, JLabel label, String myPlatillo) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Ver mas...");
        popupMenu.add(menuItem);
        panel.setComponentPopupMenu(popupMenu);

        popupMenu.show(panel, evt.getX(), evt.getY());

        if (!label.getText().equals("")) {

            Platillo platillo = null;

            for (int i = 0; i < dietas.size(); i++) {
                Dieta dieta = dietas.get(i);

                switch (myPlatillo) {
                    case "desayuno":
                        platillo = dieta.getDesayuno();
                        break;

                    case "comida":
                        platillo = dieta.getComida();
                        break;

                    case "cena":
                        platillo = dieta.getCena();
                        break;
                }

                if (label.getText().equals(platillo.getNombre())) {
                    abrirDialogInformacion(menuItem, platillo);
                    break;
                }
            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbGuardar;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblAbajoCena;
    private javax.swing.JLabel lblAbajoComida;
    private javax.swing.JLabel lblAbajoDesayuno;
    private javax.swing.JLabel lblArribaCena;
    private javax.swing.JLabel lblArribaComida;
    private javax.swing.JLabel lblArribaDesayuno;
    private javax.swing.JLabel lblCenas;
    private javax.swing.JLabel lblComidas;
    private javax.swing.JLabel lblDesayunos;
    private javax.swing.JLabel lblImagenCena1;
    private javax.swing.JLabel lblImagenCena2;
    private javax.swing.JLabel lblImagenCena3;
    private javax.swing.JLabel lblImagenComida1;
    private javax.swing.JLabel lblImagenComida3;
    private javax.swing.JLabel lblImagenComidaDos;
    private javax.swing.JLabel lblImagenDesayuno1;
    private javax.swing.JLabel lblImagenDesayuno2;
    private javax.swing.JLabel lblImagenDesayuno3;
    private javax.swing.JLabel lblNombreCena1;
    private javax.swing.JLabel lblNombreCena2;
    private javax.swing.JLabel lblNombreCena3;
    private javax.swing.JLabel lblNombreComida1;
    private javax.swing.JLabel lblNombreComida2;
    private javax.swing.JLabel lblNombreComida3;
    private javax.swing.JLabel lblNombreDesayuno1;
    private javax.swing.JLabel lblNombreDesayuno2;
    private javax.swing.JLabel lblNombreDesayuno3;
    private javax.swing.JPanel myPanelCena1;
    private javax.swing.JPanel myPanelCena2;
    private javax.swing.JPanel myPanelCena3;
    private javax.swing.JPanel myPanelComida1;
    private javax.swing.JPanel myPanelComida2;
    private javax.swing.JPanel myPanelComida3;
    private javax.swing.JPanel myPanelDesayuno1;
    private javax.swing.JPanel myPanelDesayuno3;
    private javax.swing.JPanel myPanelDesayuno4;
    // End of variables declaration//GEN-END:variables
}
