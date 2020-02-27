
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Asignatios extends javax.swing.JInternalFrame {
        Conexion conexion;
         PreparedStatement ps;
         ResultSet rs;
         ResultSetMetaData rsm;
         DefaultTableModel dtm;
     
     
    /////.........
    
    public Asignatios() {
        initComponents();
         cargarcarreras();
          cargarAlumno();
    }
    
    
    void vaciarTabla(){
            while (dtm.getRowCount() > 0) dtm.removeRow(0);  
    }
    
    
    
     //metodo para cargar la carrera
    public void cargarcarreras(){
     //   List resulist = new ArrayList();
        conexion = new Conexion();
        Connection con = conexion.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select idCarrera, nombreCarrera from carrera;");
            jComboBox2.removeAllItems();
            //DefaultComboBoxModel model = new DefaultComboBoxModel();
            //jComboBox1.setModel(model);
            while(rs.next()){
               // Carrera ca = new Carrera(rs.getString("idCarrera"),rs.getString("nombreCarrera"));
                jComboBox2.addItem(rs.getString(1)+"  "+rs.getString(2));
               // model.addElement(ca);
               // resulist.add(ca);
                
                
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    //cargar ek id del alumno
    public void cargarAlumno(){
     //   List resulist = new ArrayList();
        conexion = new Conexion();
        Connection con = conexion.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select idAlumno, nombre, paterno from alumno;");
            jComboBox3.removeAllItems();
            //DefaultComboBoxModel model = new DefaultComboBoxModel();
            //jComboBox1.setModel(model);
            while(rs.next()){
               // Carrera ca = new Carrera(rs.getString("idCarrera"),rs.getString("nombreCarrera"));
                jComboBox3.addItem(rs.getString(1)+"  "+rs.getString(2)+" "+rs.getString(3));
               // model.addElement(ca);
               // resulist.add(ca);
                
                
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    
    
    //CRUD..........
    
    //metodo para crear una carrera
        public void create (Asignacion as) throws Exception{
            PreparedStatement ps2 = null;
            conexion = new Conexion();
            Connection con = conexion.getConnection();
                try{
                    ps2=con.prepareStatement("Insert into asignacion Values(?,?,?,?)");
                    
                    ps2.setString(1, as.getTipoCurso());
                    ps2.setString(2, as.getHorario());
                    ps2.setString(3, as.getIdCarrera());
                    ps2.setString(4, as.getIdAlumno());
                   
                    
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Asignacion creada con exito");
            
        } finally{
            if(ps2!=null)
                ps2.close();
            if(con != null)
                con.close();
        }
        
    }
        
        
        
        //metodo de actualizar carrera
        public void update (Asignacion as) throws Exception{
            PreparedStatement ps2 = null;
            conexion = new Conexion();
            Connection con = conexion.getConnection();
                try{
                    ps2=con.prepareStatement("Update asignacion Set horario=?, idcarrera=?,idAlumno=? where tipoCurso=?");
                    ps2.setString(1, as.getHorario());
                   
                    ps2.setString(2, as.getIdCarrera());
                    ps2.setString(3, as.getIdAlumno());
                    ps2.setString(4, as.getTipoCurso());
                     
                    
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Asignacion modificada con exito");
            
        } finally{
            if(ps2!=null)
                ps2.close();
            if(con != null)
                con.close();
        }
        
    }
        
        
        //metodo de eliminar carrera
         public void delete (Asignacion as) throws Exception{
            PreparedStatement ps2 = null;
            conexion = new Conexion();
            Connection con = conexion.getConnection();
                try{
                    ps2=con.prepareStatement("Delete from asignacion where tipoCurso=?");
                    ps2.setString(1, as.getTipoCurso());
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Asinacion eliminada con exito");
            
        } finally{
            if(ps2!=null)
                ps2.close();
            if(con != null)
                con.close();
        }
        
    }
 
         
         //metodo para buscar
         public void search(Asignacion as)throws Exception{
             
             conexion = new Conexion();
           Connection con = conexion.getConnection();
        try {
           ps=con.prepareStatement("asignacion.tipoCurso,asignacion.horario,carrera .idcarrera,carrera .nombreCarrera,alumno.idAlumno,alumno.nombre from asignacion,alumno,carrera  where asignacion.tipoCurso=? and asignacion.idCarrera= carrera.idCarrera;");
           ps.setString(1, as.getTipoCurso());
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
            
            dtm=(DefaultTableModel)this.jTable.getModel();
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
            ps=con.prepareStatement("select asignacion.tipoCurso,asignacion.horario,asignacion.idcarrera,asignacion.idAlumno from asignacion;");
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
            
            dtm=(DefaultTableModel)this.jTable.getModel();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonListar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButtonNuevo = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Asignacion");

        jLabel1.setText("tipoCurso");

        jLabel2.setText("horario");

        jLabel3.setText("idCurso");

        jLabel4.setText("idAlumno");

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonListar.setText("Listar");
        jButtonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarActionPerformed(evt);
            }
        });

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tipoCurso", "horario", "idCurso", "idAlumno"
            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonGuardar)
                                .addGap(52, 52, 52)
                                .addComponent(jButtonListar)
                                .addGap(32, 32, 32)
                                .addComponent(jButtonActualizar)
                                .addGap(49, 49, 49)
                                .addComponent(jButtonEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(88, 88, 88)
                                        .addComponent(jButtonNuevo)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButtonBuscar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(136, 136, 136))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonNuevo)
                            .addComponent(jButtonBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonListar)
                    .addComponent(jButtonActualizar)
                    .addComponent(jButtonEliminar))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        Asignacion as= new Asignacion();
        as.setTipoCurso(jTextField1.getText());
         try {
             search(as);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas...");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        
       
        
        
        vaciarTabla();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarActionPerformed
        
        LlenarTabla();
    }//GEN-LAST:event_jButtonListarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        Asignacion as= new Asignacion();
        as.setTipoCurso(jTextField1.getText());
        as.setHorario(jTextField2.getText());
        //idCarrera toma 8 caracteres del total
        String sel=(jComboBox2.getSelectedItem().toString().substring(0,4));
        as.setIdCarrera(sel);
        //idAlumno toma 8 caracteres del total
        String selo=(jComboBox3.getSelectedItem().toString().substring(0,10));
        as.setIdAlumno(selo);
        
         try {
             create(as);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas... No se Creo la Asignacion");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        Asignacion as= new Asignacion();
        as.setTipoCurso(jTextField1.getText());
        
         try {
             delete(as);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Hubo Problemas... No se Elimino el curso");
             Logger.getLogger(careers.class.getName()).log(Level.SEVERE, null, ex);
         }
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        Asignacion a = new Asignacion();
       
        a.setTipoCurso(jTextField1.getText());
        a.setHorario(jTextField2.getText());
        
        //idCarrera
        String sel=(jComboBox2.getSelectedItem().toString().substring(0,2));
        a.setIdCarrera(sel);
        
       //idAlumno
        String so=(jComboBox3.getSelectedItem().toString().substring(0,2));
        a.setIdAlumno(so);
        
        try {
            update(a);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hubo problemas... No se modifico el alumno");
            Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        int row = jTable.rowAtPoint(evt.getPoint());

        jTextField1.setText(jTable.getValueAt(row, 0).toString());
        jTextField2.setText(jTable.getValueAt(row, 1).toString());

        /*jTextField3.setText(jTable.getValueAt(row, 2).toString());
        jTextField4.setText(jTable.getValueAt(row, 3).toString());*/

        // TODO add your handling code here:
    }//GEN-LAST:event_jTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonListar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
