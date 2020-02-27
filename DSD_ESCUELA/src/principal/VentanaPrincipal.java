
package principal;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;




public class VentanaPrincipal extends javax.swing.JFrame {
    
    public static final String RESULT
        = "reporte.pdf";

 //############# VARIABLES PARA MANEJO DE BASE DE DATOS ########################
    //Guarda la consulta operacion a realizar
    String strConsultaSQL;
    //Apuntador a la conexion
    Connection conn = null; 
    //Para ejecutar operaciones SQL
    Statement estSQL1;
    //Para guardar los resultados de una operacion SELECT
    ResultSet rs;
    Color grisClaro = new Color( 0,102,102);  
    Color azulClaro = new Color( 124,195,255 );
     
    Document document;
    PdfWriter writer;
    /**
     * Creates new form NewMDIApplication
     */
    public VentanaPrincipal() {
        initComponents();
    }

      public void abrirPDF() throws IOException{
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT); 
    }
      
      public void createPDF(){
        try
        {       //Hoja tamanio carta, rotarla (cambiar a horizontal)
            document = new Document( PageSize.LETTER.rotate() );
             
            writer = PdfWriter.getInstance(
            // that listens to the document
                    document,
                    // direccionar el PDF-stream a un archivo
                    new FileOutputStream(RESULT));
            document.open();
 
           // agregarMetaDatos(document);
            agregarContenido(document);
             
            document.close();
             
            JOptionPane.showMessageDialog(null,"Se ha generado exitosamente el arcivo PDF");
                 
        } catch (FileNotFoundException | DocumentException | HeadlessException e) 
        {
            e.printStackTrace();
        }
    }
      
      private void agregarContenido(Document document) throws DocumentException
    {
        Paragraph ParrafoHoja = new Paragraph();
                 
        // Agregamos una linea en blanco al principio del documento
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // Colocar un encabezado (en mayusculas)
        ParrafoHoja.add(new Paragraph("REPORTE"));
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // 1.- AGREGAMOS LA TABLA
        agregarTabla(ParrafoHoja);
        //Agregar 2 lineas en blanco
        agregarLineasEnBlanco(ParrafoHoja, 2);
        // 2.- AGREGAMOS LA IMAGEN
        document.add(ParrafoHoja);
 
    }
       private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) 
    {
        for (int i = 0; i < nLineas; i++) 
            parrafo.add(new Paragraph(" "));
    }
       
       private void agregarTabla(Paragraph parrafo) throws BadElementException 
    {
        //Anchos de las columnas
        float anchosFilas[] = {0.5f,1f,1f,1f,1f,0.8f,1f};
        
        PdfPTable tabla = new PdfPTable(anchosFilas);
        //creamos lso titulos de las columnas de las tablas
        String rotulosColumnas[] = {"idAlumno","Nombre","Paterno","Materno","Email","FechaNa","nombreCarrera"};
        // Porcentaje que ocupa a lo ancho de la pagina del PDF
        tabla.setWidthPercentage(100);
        //Alineacion horizontal centrada
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        //agregar celda que ocupa las 9 columnas de los rotulos
        PdfPCell cell = new PdfPCell(new Paragraph("Tabla: Alumnos"));
        cell.setColspan(9);
        //Centrar contenido de celda
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
        cell.setBackgroundColor (BaseColor.BLUE);    
        //
        tabla.addCell(cell);
 
        try
        {
            if ( ConectarBD() )
            {
                // Mostrar los rotulos de las columnas
                for(int i=0; i<rotulosColumnas.length; i++)
                {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    //cell.setBackgroundColor (grisClaro);
                    tabla.addCell(cell);
                }
                 
                //Consultar la tabla empleados
                strConsultaSQL = "Select alumno.idAlumno, alumno.nombre, alumno.paterno, alumno.materno, alumno.email, alumno.fechaNac, carrera.nombreCarrera from alumno,carrera where alumno.idCarrera = carrera.idCarrera;";
                //ejecutar consulta
                rs = estSQL1.executeQuery(strConsultaSQL);
 
                //Iterar Mientras haya una fila siguiente
            while (rs.next())
            {           //Agregar 9 celdas
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("idAlumno"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("nombre")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("paterno")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("materno")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("email")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getDate("fechaNac").toString()) );
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("nombreCarrera")));
                    tabla.addCell(cell);
            }
                //Cerrar los objetos de manejo de BD
                rs.close();
                estSQL1.close();
                conn.close();
           
            }       //Fin de if que comprueba si se pudo conectar
             
        }
        catch(Exception e) 
        {
            System.out.println("Excepcion al ejecutar CONSULTA!!!");
        }
        //Agregar la tabla con los datos al parrafo que nos llego como entrada
        parrafo.add(tabla);
    }  //Fin del metodo que crea la tabla
       
        public boolean ConectarBD() throws Exception
    {
        try
        {       
                //Aqui se instancia a la otra clase --> CrearConexion.java               
                //y se le mandan en el constructor los datos de conexion
                Conexion con  = new Conexion();
                //Obtiene una referencia a la conexion abierta
                conn = con.getConnection();
             
            //Comprobar si hay una refencia valida
            if(conn!=null)
            {
                    //Mostrar MSG al usuario de la conexion se ha establecido
                    System.out.println("Conexion establecida");
                    //Se prepara para ejecutar sentencias en la conexion recien abierta
                estSQL1 = conn.createStatement();
                    return true;
            }
            else
                return false;
                 
         }
        catch (SQLException e)
        {
             
          // the above drop statements will throw exceptions
          // if the types and tables did not exist before. Just ingore it.
          System.out.println("Error en la conexion!!!");
          e.printStackTrace();
          return false;
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

        desktopPane = new javax.swing.JDesktopPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        desktopPane.add(jDesktopPane1);
        jDesktopPane1.setBounds(10, 10, 880, 570);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Archivo");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Formas");

        jMenuItem1.setText("Alumnos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

        jMenuItem2.setText("Cursos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem2);

        jMenuItem6.setText("Asignacion");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem6);

        menuBar.add(editMenu);

        jMenu1.setText("Herramientas");

        jMenuItem3.setText("Reporte");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        menuBar.add(jMenu1);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ayuda");

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Acerca de");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
       
        // Se crea el objeto        
        About ab = new About();
        ControlaInstancia(ab);
//        // se añade al jDesktopPane
//        jDesktopPane1.add(ab);
//        //se muestra en pantalla
//        ab.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        students st = new students();
        ControlaInstancia(st);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        careers car = new careers();
        ControlaInstancia(car);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       Reportes rp = new Reportes(); 
        ControlaInstancia(rp);
//        try {
//            createPDF();
//            abrirPDF();
//        } catch (IOException ex) {
//            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Asignatios as = new Asignatios();
        ControlaInstancia(as);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public void ControlaInstancia(JInternalFrame inter){

boolean mostrar=true;
String Nombre=inter.getTitle();
for (int a=0;a<jDesktopPane1.getComponentCount();a++){     // verificar si es instancia de algun componente que ya este en el jdesktoppane
    if( inter.getClass().isInstance( jDesktopPane1.getComponent(a) )){
        JOptionPane.showMessageDialog(rootPane, "La ventana "+Nombre+" que interta abrir ya está abierta, cierre la ventana actual e intente nuevamente");
        System.out.println("esta instancia, no se debe mostrar");
        inter.toFront();
        jDesktopPane1.moveToFront(inter);
        mostrar=false;
    }else{
        System.out.println("No esta abierta, puede mostrarse");
    }
}
if(mostrar){ 
   // inter.setSize(GetMedidaEcritorio());
    jDesktopPane1.add(inter); }
inter.show();
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
        JFrame.setDefaultLookAndFeelDecorated(true);
      try{
          UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
      } catch(Exception e){
          
      } 
//      try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Socket s;
                try {
                    s = new Socket("127.0.0.1",5000);
                    new VentanaPrincipal().setVisible(true);
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "No se puede establecer conexion...");
                }
               // new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
