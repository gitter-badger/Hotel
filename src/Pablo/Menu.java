package Pablo;
// @author Juan Pablo
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class Menu extends javax.swing.JFrame {
    DefaultTableModel modelo;
    public Menu() {
        initComponents();
        limpiar();
        bloquear();
        cargar("");
    }
    void cargar(String valor){
        String [] titulos={"CODIGO","CEDULA","CIUCAD ORIGEN","ESTADÍA","TIPO HABITACIÓN"};
        String [] registros=new String[5];
        String sql="SELECT * FROM huespedes WHERE cedula LIKE '%"+valor+"%'";
        modelo=new DefaultTableModel(null,titulos);
        Hotel con=new Hotel();
        Connection cn=con.conexion();
        try {
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("codigo");
                registros[1]=rs.getString("cedula");
                registros[2]=rs.getString("ciu_origen");
                registros[3]=rs.getString("categoria");
                registros[4]=rs.getString("estadia");
                modelo.addRow(registros);
            }
            area.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void ganadores(){
        String [] titulos={"GANADORES DE BONOS"};
        String [] registros=new String[1];
        String sql="SELECT cedula FROM huespedes WHERE estadia>5 AND categoria='Basica'";
        modelo=new DefaultTableModel(null,titulos);
        Hotel con=new Hotel();
        Connection cn=con.conexion();
        try {
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("cedula");
                modelo.addRow(registros);
            }
            area.setModel(modelo);
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void infor_uno(){
        String [] titulos={"ECONOMICA","BASICA","PREMIUM"};
        String [] registros=new String[3];
        String sql1="SELECT COUNT(*) FROM huespedes";
        String sql2="SELECT COUNT(*) FROM huespedes WHERE categoria='Premium'";
        String sql3="SELECT COUNT(*) FROM huespedes WHERE categoria='Basica'";
        String sql4="SELECT COUNT(*) FROM huespedes WHERE categoria='Economica'";
        modelo=new DefaultTableModel(null,titulos);
        Hotel con=new Hotel();
        Connection cn=con.conexion();
        try {
            Statement st1=cn.createStatement();
            ResultSet rs1=st1.executeQuery(sql1);
            Statement st2=cn.createStatement();
            ResultSet rs2=st2.executeQuery(sql2);
            Statement st3=cn.createStatement();
            ResultSet rs3=st3.executeQuery(sql3);
            Statement st4=cn.createStatement();
            ResultSet rs4=st4.executeQuery(sql4);
            while(rs1.next() && rs2.next() && rs3.next() && rs4.next()){
                Double a,b,c,e,f,h;
                a=rs1.getDouble(1);
                b=rs2.getDouble(1);
                c=(b*100)/a;
                String d=String.valueOf(c);
                e=rs3.getDouble(1);
                f=(e*100)/a;
                String g=String.valueOf(f);
                h=rs4.getDouble(1);
                String i=String.valueOf(h);
                registros[0]=d+"%";
                registros[1]=g+"%";
                registros[2]=i+"%";
                modelo.addRow(registros);
            }
            area.setModel(modelo);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void infor_dos(){
        aux.setVisible(false);
        mayor_h.setVisible(true);
        String [] titulos={"CIUDAD","HUESPEDES"};
        String [] registros=new String[3];
        String sql="SELECT ciu_origen, count(*)\n" +
                   "FROM huespedes\n" +
                    "WHERE ciu_origen='Bogota' OR ciu_origen='Medellin' " +
                   "GROUP BY ciu_origen\n";
        modelo=new DefaultTableModel(null,titulos);
        Hotel con=new Hotel();
        Connection cn=con.conexion();
        try {
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("ciu_origen");
                registros[1]=rs.getString("count(*)");               
                modelo.addRow(registros);
            }
            area.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void mayor(){
        String [] titulos={"MAYOR CANTIDAD"};
        String [] registros=new String[1];
        String sql2="SELECT count(*)\n" +
                    "FROM huespedes\n" +
                    "WHERE ciu_origen='Bogota'";
        String sql3="SELECT count(*)\n" +
                    "FROM huespedes\n" +
                    "WHERE ciu_origen='Medellin'";
        modelo=new DefaultTableModel(null,titulos);
        Hotel con=new Hotel();
        Connection cn=con.conexion();
        try {
            Statement st1=cn.createStatement();
            ResultSet rs1=st1.executeQuery(sql2);
            Statement st2=cn.createStatement();
            ResultSet rs2=st2.executeQuery(sql3);
            while(rs1.next() && rs2.next()){
                int a,b;
                String c;
                a = rs1.getInt(1);
                b = rs2.getInt(1);
                if(a>b){
                    c="Bogota";
                }
                else if(a<b){
                    c="Medellin";
                }
                else{
                    c="Medellin Y Bogota";
                }               
                registros[0]=c;
                modelo.addRow(registros);
            }
            area.setModel(modelo);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void limpiar(){
        cedula.setText("");
        c_origen.setText("");
        estadia.setText("");
        tipo.clearSelection();
    }
    void bloquear(){
        cedula.setEnabled(false);
        c_origen.setEnabled(false);
        estadia.setEnabled(false);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        aux.setEnabled(true);
        guardar.setVisible(false);
        cancelar.setVisible(false);
        registrar.setVisible(true);
        bono.setVisible(true);
        info_uno.setVisible(true);
        info_dos.setVisible(true);
        salir.setVisible(true);
        volver.setVisible(false);
        mayor_h.setVisible(false);
        volv.setVisible(false);
    }
    void desbloquear(){
        cedula.setEnabled(true);
        c_origen.setEnabled(true);
        estadia.setEnabled(true);
        bt1.setEnabled(true);
        bt2.setEnabled(true);
        bt3.setEnabled(true);
        aux.setEnabled(false);
        guardar.setVisible(true);
        cancelar.setVisible(true);
        registrar.setVisible(false);
        bono.setVisible(false);
        info_uno.setVisible(false);
        info_dos.setVisible(false);
        salir.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cedula = new javax.swing.JTextField();
        c_origen = new javax.swing.JTextField();
        estadia = new javax.swing.JTextField();
        bt1 = new javax.swing.JRadioButton();
        bt2 = new javax.swing.JRadioButton();
        bt3 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
        bono = new javax.swing.JButton();
        info_uno = new javax.swing.JButton();
        info_dos = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        aux = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTable();
        mayor_h = new javax.swing.JButton();
        volv = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Cedula:");

        jLabel2.setText("Ciudad de Origen:");

        jLabel3.setText("Estadía: ");

        jLabel4.setText("Tipo de Habitación: ");

        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });

        c_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_origenActionPerformed(evt);
            }
        });

        estadia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadiaActionPerformed(evt);
            }
        });

        tipo.add(bt1);
        bt1.setText("Economica");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });

        tipo.add(bt2);
        bt2.setText("Basica");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });

        tipo.add(bt3);
        bt3.setText("Premium");
        bt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(bt1)
                        .addGap(73, 73, 73)
                        .addComponent(bt3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estadia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt2)))
                            .addComponent(jLabel4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(c_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt1)
                    .addComponent(bt2)
                    .addComponent(bt3))
                .addGap(193, 193, 193))
        );

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registrar.setText("Registrar Huesped");
        registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jPanel3.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 155, -1));

        bono.setText("Ganadores de Bono");
        bono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bonoActionPerformed(evt);
            }
        });
        jPanel3.add(bono, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 41, 155, -1));

        info_uno.setText("Informacion 1");
        info_uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_unoActionPerformed(evt);
            }
        });
        jPanel3.add(info_uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 12, 120, -1));

        info_dos.setText("Informacion 2");
        info_dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_dosActionPerformed(evt);
            }
        });
        jPanel3.add(info_dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 41, 120, -1));

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jPanel3.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 70, -1, -1));

        guardar.setText("GUARDAR");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel3.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 250, -1));

        cancelar.setText("CANCELAR");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel3.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 250, -1));

        volver.setFont(new java.awt.Font("Yu Gothic Light", 1, 24)); // NOI18N
        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        jPanel3.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 150, 60));

        jPanel4.setBackground(new java.awt.Color(102, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("BUSCAR: ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 363, -1, -1));

        aux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                auxKeyReleased(evt);
            }
        });
        jPanel4.add(aux, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 355, 176, -1));

        area.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(area);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, -1, 324));

        mayor_h.setText("Mayor Cantidad de Huespedes");
        mayor_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mayor_hActionPerformed(evt);
            }
        });
        jPanel4.add(mayor_h, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, -1, -1));

        volv.setText("Volver");
        volv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volvActionPerformed(evt);
            }
        });
        jPanel4.add(volv, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));

        jPanel6.setBackground(new java.awt.Color(0, 0, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel7.setText("Hotel");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        // TODO add your handling code here:
        cedula.transferFocus();
    }//GEN-LAST:event_cedulaActionPerformed

    private void info_dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info_dosActionPerformed
        // TODO add your handling code here
        infor_dos();
        info_dos.transferFocus();
    }//GEN-LAST:event_info_dosActionPerformed

    private void bonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bonoActionPerformed
        // TODO add your handling code here:
        bono.transferFocus();
        ganadores();
        cedula.setEnabled(false);
        c_origen.setEnabled(false);
        estadia.setEnabled(false);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        aux.setEnabled(false);
        guardar.setVisible(false);
        cancelar.setVisible(false);
        registrar.setVisible(false);
        bono.setVisible(false);
        info_uno.setVisible(false);
        info_dos.setVisible(false);
        salir.setVisible(false);
        volver.setVisible(true);
        mayor_h.setVisible(false);
        volv.setVisible(false);
    }//GEN-LAST:event_bonoActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
        registrar.transferFocus();
        desbloquear();
        mayor_h.setVisible(false);
        volv.setVisible(false);
    }//GEN-LAST:event_registrarActionPerformed

    private void c_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_origenActionPerformed
        // TODO add your handling code here:
        c_origen.transferFocus();
    }//GEN-LAST:event_c_origenActionPerformed

    private void info_unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info_unoActionPerformed
        // TODO add your handling code here:
        infor_uno();
        info_uno.transferFocus();
        mayor_h.setVisible(false);
        volv.setVisible(false);        
    }//GEN-LAST:event_info_unoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // CERRAR
        salir.transferFocus();
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // GUARDAR
        guardar.transferFocus();
        Hotel cone=new Hotel();
        Connection cn=cone.conexion();
        String cc,c_org,est,tipo_h= null;
        String sql="";
        cc=cedula.getText();
        c_org=c_origen.getText();
        est=estadia.getText();
        if(bt1.isSelected()){
            tipo_h="Economica";
        }
        else if(bt2.isSelected()){
            tipo_h="Basica";
        }
        else if(bt3.isSelected()){
            tipo_h="Premium";
        }
        else{
            JOptionPane.showMessageDialog(null,"Error... Seleccione el Tipo de habitacion deseada");
        }
        //id=rb.setSelected();
        sql="INSERT INTO huespedes(cedula,ciu_origen,categoria,estadia) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst=cn.prepareStatement(sql);
            //pst.setString(1,id);
            pst.setString(1,cc);
            pst.setString(2,c_org);
            pst.setString(3,tipo_h);
            pst.setString(4,est);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
                cargar("");
                limpiar();
                bloquear();
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mayor_h.setVisible(false);
        volv.setVisible(false);
    }//GEN-LAST:event_guardarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        cancelar.transferFocus();
        limpiar();
        bloquear();
        mayor_h.setVisible(false);
        volv.setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void estadiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadiaActionPerformed
        // TODO add your handling code here:
        estadia.transferFocus();
    }//GEN-LAST:event_estadiaActionPerformed

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
        // TODO add your handling code here:
        bt1.transferFocus();
    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        // TODO add your handling code here:
        bt2.transferFocus();
    }//GEN-LAST:event_bt2ActionPerformed

    private void bt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt3ActionPerformed
        // TODO add your handling code here:
        bt3.transferFocus();
    }//GEN-LAST:event_bt3ActionPerformed

    private void auxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auxKeyReleased
        // TODO add your handling code here:
        cargar(aux.getText());
    }//GEN-LAST:event_auxKeyReleased

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // TODO add your handling code here:
        bloquear();
        cargar("");
    }//GEN-LAST:event_volverActionPerformed

    private void mayor_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mayor_hActionPerformed
        // TODO add your handling code here:
        mayor();
        mayor_h.transferFocus();
        volv.setVisible(true);
    }//GEN-LAST:event_mayor_hActionPerformed

    private void volvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volvActionPerformed
        // TODO add your handling code here:
        infor_dos();
        mayor_h.setVisible(true);
        volv.setVisible(false);
    }//GEN-LAST:event_volvActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable area;
    private javax.swing.JTextField aux;
    private javax.swing.JButton bono;
    private javax.swing.JRadioButton bt1;
    private javax.swing.JRadioButton bt2;
    private javax.swing.JRadioButton bt3;
    private javax.swing.JTextField c_origen;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField cedula;
    private javax.swing.JTextField estadia;
    private javax.swing.JButton guardar;
    private javax.swing.JButton info_dos;
    private javax.swing.JButton info_uno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mayor_h;
    private javax.swing.JButton registrar;
    private javax.swing.JButton salir;
    private javax.swing.ButtonGroup tipo;
    private javax.swing.JButton volv;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
