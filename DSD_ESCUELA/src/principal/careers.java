
package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class careers extends javax.swing.JInternalFrame {

     Conexion conexion;
     PreparedStatement ps;
     ResultSet rs;
     ResultSetMetaData rsm;
     DefaultTableModel dtm;
     
     
    ///
    public careers() {
        initComponents();
    }
     
    void vaciarTabla(){
            while (dtm.getRowCount() > 0) dtm.removeRow(0);  
    }
    
    //CRUD..........
    
    //metodo para crear una carrera
        public void create (Carrera c) throws Exception{
            PreparedStatement ps2 = null;
            conexion = new Conexion();
            Connection con = conexion.getConnection();
                try{
                    ps2=con.prepareStatement("Insert into carrera Values(?,?,?,?,?)");
                    ps2.setString(1, c.getIdCarrera());
                    ps2.setString(2, c.getNombreCarrera());
                    ps2.setString(3, c.getFechainicio());
                    ps2.setString(4, c.getFechafin());
                    ps2.setString(5, c.getCouta());
                    
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Curso creado con exito");
            
        } finally{
            if(ps2!=null)
                ps2.close();
            if(con != null)
                con.close();
        }
        
    }
        
        
        
        //metodo de actualizar carrera
        public void update (Carrera c) throws Exception{
            PreparedStatement ps2 = null;
            conexion = new Conexion();
            Connection con = conexion.getConnection();
                try{
                    ps2=con.prepareStatement("Update carrera Set nombreCarrera=?, fechainicio=?,fechafin=?, couta=? where idCarrera=?");
                    ps2.setString(1, c.getNombreCarrera());
                   
                    ps2.setString(2, c.getFechainicio());
                    ps2.setString(3, c.getFechafin());
                    ps2.setString(4, c.getCouta());
                     ps2.setString(5, c.getIdCarrera());
                    
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Curso modificada con exito");
            
        } finally{
            if(ps2!=null)
                ps2.close();
            if(con != null)
                con.close();
        }
        
    }
        
        
        //metodo de eliminar carrera
         public void delete (Carrera c) throws Exception{
            PreparedStatement ps2 = null;
            conexion = new Conexion();
            Connection con = conexion.getConnection();
                try{
                    ps2=con.prepareStatement("Delete from carrera where idCarrera=?");
                    ps2.setString(1, c.getIdCarrera());
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Curso eliminado con exito");
            
        } finally{
            if(ps2!=null)
                ps2.close();
            if(con != null)
                con.close();
        }
        
    }
 
         
         //metodo para buscar
         public void search(Carrera c)throws Exception{
             
             conexion = new Conexion();
           Connection con = conexion.getConnection();
        try {
            
            ps=con.prepareStatement("select idCarrera, nombreCarrera from carrera where idCarrera=?;");
            ps.setString(1, c.getIdCarrera());
            rs=ps.executeQuery();
            rsm=rs.getMetaData();
            
            ArrayList<Object[]> data = new ArrayList<>();
            while(rs.next()){
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i]=rs.getObject(i+1);  
                }
                data.add(rows);
            }
            
            dtm=(DefaultTableModel)this.jTable1.getModel();
            vaciarTabla();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
         
    //metodo para llenar tabla     
    public void LlenarTabla(){
           conexion = new Conexion();
           Connection con = conexion.getConnection();
        try {
            ps=con.prepareStatement("select idCarrera, nombreCarrera, fechainicio, fechafin, couta from carrera;");
            rs=ps.executeQuery();
            rsm=rs.getMetaData();
            
            ArrayList<Object[]> data = new ArrayList<>();
            while(rs.next()){
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i]=rs.getObject(i+1);  
                }
                data.add(rows);
            }
            
            dtm=(DefaultTableModel)this.jTable1.getModel();
            vaciarTabla();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldfechaFin = new javax.swing.JTextField();
        jTextFieldfechaInicio1 = new javax.swing.JTextField();
        jTextFieldCouta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Curso(s)");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idCurso", "nombreCurso", "fechaInicio", "fechaFin", "couta"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Listar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Actualizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("idCurso");

        jLabel2.setText("nombreCurso");

        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha de Inicio dd/mm/aa");

        jLabel4.setText("Fecha de Fin dd/mm/aa");

        jLabel5.setText("couta");

        jTextFieldfechaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldfechaFinActionPerformed(evt);
            }
        });

        jTextFieldfechaInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldfechaInicio1ActionPerformed(evt);
            }
        });

        jLabel6.setText("$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(42, 42, 42)
                        .addComponent(jButton3)
                        .addGap(63, 63, 63)
                        .addComponent(jButton5)
                        .addGap(58, 58, 58)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCouta, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))
                            .addComponent(jTextFieldfechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldfechaInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addComponent(jButton1)
                                .addGap(41, 41, 41)
                                .addComponent(jButton6))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldfechaInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldfechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton6))
                        .addGap(82, 82, 82)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldCouta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton5)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //boton de nuevo,limpia pantalla
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextFieldfechaInicio1.setText("");
        jTextFieldfechaFin.setText("");
        jTextFieldCouta.setText("");
        
        
        vaciarTabla();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        LlenarTabla();
    }//GEN-LAST:event_jButton3ActionPerformed

    //GUARDAR DATOS
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Carrera c= new Carrera();
        c.setIdCarrera(jTextField1.getText());
        c.setNombreCarrera(jTextField2.getText());
        c.setFechainicio(jTextFieldfechaInicio1.getText());
        c.setFechafin(jTextFieldfechaFin.getText());
        c.setCouta(jTextFieldCouta.getText());
        
        
         try {
             create(c);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas... No se Creo el Curso");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.rowAtPoint(evt.getPoint());
        
        jTextField1.setText(jTable1.getValueAt(row, 0).toString());
        jTextField2.setText(jTable1.getValueAt(row, 1).toString());
        
        jTextFieldfechaInicio1.setText(jTable1.getValueAt(row, 2).toString());
        jTextFieldfechaFin.setText(jTable1.getValueAt(row, 3).toString());
        jTextFieldCouta.setText(jTable1.getValueAt(row, 4).toString());
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Carrera c= new Carrera();
        c.setIdCarrera(jTextField1.getText());
        
         try {
             delete(c);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas... No se Elimino el curso");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        Carrera c= new Carrera();
        c.setIdCarrera(jTextField1.getText());
        c.setNombreCarrera(jTextField2.getText());
        c.setFechainicio(jTextFieldfechaInicio1.getText());
        c.setFechafin(jTextFieldfechaFin.getText());
        c.setCouta(jTextFieldCouta.getText());
        
        
         try {
             update(c);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas... No se Modifico el curso");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    
    //metodo de busqueda de una carrera ,,por el id
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

         Carrera c= new Carrera();
        c.setIdCarrera(jTextField1.getText());
         try {
             search(c);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas...");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextFieldfechaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldfechaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldfechaFinActionPerformed

    private void jTextFieldfechaInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldfechaInicio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldfechaInicio1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldCouta;
    private javax.swing.JTextField jTextFieldfechaFin;
    private javax.swing.JTextField jTextFieldfechaInicio1;
    // End of variables declaration//GEN-END:variables
}
