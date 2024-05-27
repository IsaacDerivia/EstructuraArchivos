/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Encuesta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author isaac
 */
public class Archivos extends javax.swing.JFrame {
private File archivoAbierto;
    /**
     * Creates new form Archivos
     */
    public Archivos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        //se crean dos botones, uno es para id y el otro para borrar registro
        JButton btnId = new JButton("ID");
        JButton btnBorrar = new JButton("Borrar registro");
        //se crean dos botones, uno es para id y el otro para borrar registro
        btnId.setBounds(0, 50, 200, 25);
        btnBorrar.setBounds(270, 50, 220, 25);

        getContentPane().add(btnId);
        getContentPane().add(btnBorrar);
        //se crean dos botones, uno es para id y el otro para borrar registro
        btnId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIdActionPerformed(evt);
            }
        });
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
        setSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(null);

        btnAbrir.setText("abrir archivo");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        getContentPane().add(btnAbrir);
        btnAbrir.setBounds(0, 10, 200, 25);

        btnGuardar.setText("guardar archivo");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(270, 10, 220, 25);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "producto", "cantidad", "precio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 100, 500, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
        file.setFileFilter(filter);
        int respuesta = file.showOpenDialog(this);
        if(respuesta == JFileChooser.APPROVE_OPTION){
            File archivo = file.getSelectedFile();
            archivoAbierto = file.getSelectedFile();

            FileReader fr = null;
            try {
                fr = new FileReader(archivo);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int contador = 0;
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            try {
                while((linea = br.readLine()) != null){
                    String[] arreglo = linea.split(",");
                    modelo.addRow(arreglo);
                    contador++;
                }
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if(archivoAbierto != null){
            FileWriter fw = null;
            try {
                fw = new FileWriter(archivoAbierto);
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            BufferedWriter bw = new BufferedWriter(fw);
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            for(int i = 0; i < modelo.getRowCount(); i++){
                for(int j = 0; j < modelo.getColumnCount(); j++){
                    try {
                        bw.write(modelo.getValueAt(i, j).toString());
                        if(j < modelo.getColumnCount() - 1){
                            bw.write(",");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Archivos.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    bw.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(Archivos.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } else {
            //mensaje de error
            JOptionPane.showMessageDialog(this, "No se ha abierto ningun archivo");

        }

    }//GEN-LAST:event_btnGuardarActionPerformed


    //se crea el metodo para el boton de id
    private void btnIdActionPerformed(java.awt.event.ActionEvent evt) {
      //JOPTIONPANE PARA EL ID
        String id = JOptionPane.showInputDialog("Ingrese el ID del producto");
        //selecciona la fila donde se encuentra el id
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        for(int i = 0; i < modelo.getRowCount(); i++){
            if(modelo.getValueAt(i, 0).toString().equals(id)){
                jTable1.setRowSelectionInterval(i, i);
                break;
            } else {
                //mensaje de error
                JOptionPane.showMessageDialog(this, "No se ha encontrado el ID");
            }
        }

    }

    //se crea el metodo para el boton de borrar registro
    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        //preguntar si el usuario esta seguro de boorrar la tabla
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar el registro?");
        if(respuesta == JOptionPane.YES_OPTION){
            //selecciona la fila que se va a borrar
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int fila = jTable1.getSelectedRow();
            modelo.removeRow(fila);
        } else {
            //mensaje de error
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun registro");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Archivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Archivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Archivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Archivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Archivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}