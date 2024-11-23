package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicApp extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private List<String[]> patientList;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public ClinicApp() {
        setTitle("Shinazugawa Nindy Clinic");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        patientList = new ArrayList<String[]>();

        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createPatientProfilePanel(), "PatientProfile");
        mainPanel.add(createAddViewOptionsPanel(), "AddViewOptions");
        mainPanel.add(createPatientInputFormPanel(), "PatientInputForm");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(new Color(240, 250, 255));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(0, 102, 204));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("SHINAZUGAWA NINDY CLINIC", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JLabel welcomeLabel = new JLabel("Welcome, please login to access the clinic's management system", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        welcomeLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeLabel);

        loginPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setColumns(30);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setColumns(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (password.equals("12345")) {
                JOptionPane.showMessageDialog(loginPanel, "Login successful! Welcome, " + username + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, "PatientProfile");
            } else {
                JOptionPane.showMessageDialog(loginPanel, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        loginPanel.add(formPanel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exitButton.setBackground(new Color(204, 0, 0));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.setBackground(new Color(240, 250, 255));
        exitPanel.add(exitButton);

        loginPanel.add(exitPanel, BorderLayout.SOUTH);

        return loginPanel;
    }

    private JPanel createPatientProfilePanel() {
        JPanel patientProfilePanel = new JPanel(new BorderLayout());
        patientProfilePanel.setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 102));

        JLabel titleLabel = new JLabel("SHINAZUGAWA NINDY CLINIC", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JButton backButton = new JButton("Logout");
        backButton.setBackground(new Color(204, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "Login");
        });

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.EAST);
        patientProfilePanel.add(headerPanel, BorderLayout.NORTH);

        JButton addPatientButton = new JButton("Input Pasien Baru");
        addPatientButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addPatientButton.setBackground(new Color(0, 153, 76));
        addPatientButton.setForeground(Color.WHITE);
        addPatientButton.addActionListener(e -> cardLayout.show(mainPanel, "PatientInputForm"));

        JButton viewPatientButton = new JButton("Lihat Data Pasien");
        viewPatientButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        viewPatientButton.setBackground(new Color(102, 153, 255));
        viewPatientButton.setForeground(Color.WHITE);
        viewPatientButton.addActionListener(e -> {
            if (!patientList.isEmpty()) {
                StringBuilder allPatients = new StringBuilder("Data Pasien:\n\n");
                for (int i = 0; i < patientList.size(); i++) {
                    allPatients.append("Pasien ").append(i + 1).append(":\n");
                    String[] patientData = patientList.get(i);
                    allPatients.append("NIK: ").append(patientData[0]).append("\n")
                            .append("Nama Lengkap: ").append(patientData[1]).append("\n")
                            .append("TTL: ").append(patientData[2]).append("\n")
                            .append("Umur: ").append(patientData[3]).append("\n")
                            .append("Nomor Telepon: ").append(patientData[4]).append("\n")
                            .append("Alamat Lengkap: ").append(patientData[5]).append("\n")
                            .append("Jenis kelamin: ").append(patientData[6]).append("\n")
                            .append("Golongan Darah: ").append(patientData[7]).append("\n")
                            .append("Pekerjaan: ").append(patientData[8]).append("\n")
                            .append("Kewarganegaraan: ").append(patientData[9]).append("\n")
                            .append("Agama: ").append(patientData[10]).append("\n")
                            .append("Status Perkawinan: ").append(patientData[11]).append("\n\n");
                }
                JTextArea textArea = new JTextArea(allPatients.toString());
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 400));
                JOptionPane.showMessageDialog(this, scrollPane, "Data Pasien", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Belum ada data pasien yang disimpan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(addPatientButton);
        centerPanel.add(viewPatientButton);
        patientProfilePanel.add(centerPanel, BorderLayout.CENTER);
        return patientProfilePanel;
    }

    private JPanel createAddViewOptionsPanel() {
        JPanel optionsPanel = new JPanel(new BorderLayout());
        optionsPanel.setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("FITUR TERSEDIA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        optionsPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel optionsListPanel = new JPanel();
        optionsListPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsListPanel.setBackground(new Color(250, 250, 250));


        JButton adminButton = new JButton("Sistem Administrasi Pasien");
        JButton healthButton = new JButton("Layanan Kesehatan");
        JButton pharmacyButton = new JButton("Farmasi");
        JButton financeButton = new JButton("Keuangan");

        adminButton.addActionListener(e -> JOptionPane.showMessageDialog(optionsPanel,
                "Fitur Administrasi:\n" +
                        "- Pendaftaran Pasien\n" +
                        "- Janji Kunjungan Pasien Online\n" +
                        "- Manajemen Antrian Online\n" +
                        "- Jadwal Poliklinik dan Dokter"));

        healthButton.addActionListener(e -> JOptionPane.showMessageDialog(optionsPanel,
                "Fitur Layanan Kesehatan:\n" +
                        "- Pelayanan Rawat Jalan\n" +
                        "- Pelayanan Rawat Inap\n" +
                        "- Pelayanan Medical Check-Up\n" +
                        "- Pelayanan Laboratorium\n" +
                        "- Rekam Medis Elektronik"));

        pharmacyButton.addActionListener(e -> JOptionPane.showMessageDialog(optionsPanel,
                "Fitur Farmasi:\n" +
                        "- Peresepan Elektronik\n" +
                        "- Penyerahan Obat Apotek\n" +
                        "- Manajemen Persediaan Farmasi"));

        financeButton.addActionListener(e -> JOptionPane.showMessageDialog(optionsPanel,
                "Fitur Keuangan:\n" +
                        "- Transaksi Penjualan dan Kasir\n" +
                        "- Laporan Keuangan Lengkap\n" +
                        "- Manajemen Keuangan\n" +
                        "- Eksekutif Dashboard"));

        optionsListPanel.add(adminButton);
        optionsListPanel.add(healthButton);
        optionsListPanel.add(pharmacyButton);
        optionsListPanel.add(financeButton);
        optionsPanel.add(optionsListPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Kembali ke Profil Pasien");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "PatientProfile"));
        optionsPanel.add(backButton, BorderLayout.SOUTH);
        return optionsPanel;
    }

    private JPanel createPatientInputFormPanel() {
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("[ Input Pasien Baru ]", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.RED);
        formPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel inputFormPanel = new JPanel();
        inputFormPanel.setLayout(new GridBagLayout());
        inputFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputFormPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"NIK:", "Nama Lengkap:", "TTL:", "Umur (Tahun):",
                "Nomor Telepon:", "Alamat Lengkap:", "Jenis Kelamin:", "Golongan Darah:", "Pekerjaan:", "Kewarganegaraan", "Agama:", "Status Perkawinan:"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            gbc.gridx = 0;
            gbc.gridy = i;
            inputFormPanel.add(label, gbc);
            textFields[i] = new JTextField(30);
            gbc.gridx = 1;
            inputFormPanel.add(textFields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Simpan");
        JButton clearButton = new JButton("Hapus Data");
        JButton backButton = new JButton("Kembali");
        JButton nextButton = new JButton("Next");

        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        saveButton.setBackground(new Color(0, 153, 76));
        saveButton.setForeground(Color.WHITE);

        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setBackground(new Color(204, 0, 0));
        clearButton.setForeground(Color.WHITE);

        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);

        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nextButton.setBackground(new Color(255, 204, 0));
        nextButton.setForeground(Color.BLACK);

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        saveButton.addActionListener(e -> {
            String[] patientData = new String[textFields.length];
            for (int i = 0; i < textFields.length; i++) {
                patientData[i] = textFields[i].getText();
            }
            patientList.add(patientData);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        clearButton.addActionListener(e -> {
            for (JTextField textField : textFields) {
                textField.setText("");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "PatientProfile"));
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "AddViewOptions"));
        formPanel.add(new JScrollPane(inputFormPanel), BorderLayout.CENTER);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);
        return formPanel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClinicApp::new);
    }
}