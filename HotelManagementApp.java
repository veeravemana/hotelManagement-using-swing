import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HotelManagementApp extends JFrame {

    private JTextField txtName, txtRoomNumber;
    private JTextArea txtAreaBookings;
    private JButton btnBook, btnViewBookings, btnCheckout;

    private List<Booking> bookings;

    public HotelManagementApp() {
        bookings = new ArrayList<>();

        setTitle("Hotel Management System");
        setLayout(new FlowLayout());

        // Create GUI components
        JLabel lblName = new JLabel("Customer Name:");
        txtName = new JTextField(20);

        JLabel lblRoomNumber = new JLabel("Room Number:");
        txtRoomNumber = new JTextField(5);

        btnBook = new JButton("Book Room");
        btnViewBookings = new JButton("View Bookings");
        btnCheckout = new JButton("Checkout");

        txtAreaBookings = new JTextArea(10, 30);
        txtAreaBookings.setEditable(false);

        // Add components to frame
        add(lblName);
        add(txtName);
        add(lblRoomNumber);
        add(txtRoomNumber);
        add(btnBook);
        add(btnViewBookings);
        add(btnCheckout);
        add(new JScrollPane(txtAreaBookings));

        // Button actions
        btnBook.addActionListener(e -> bookRoom());
        btnViewBookings.addActionListener(e -> viewBookings());
        btnCheckout.addActionListener(e -> checkoutRoom());

        // Setup JFrame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void bookRoom() {
        String name = txtName.getText();
        String roomNumber = txtRoomNumber.getText();

        if (name.isEmpty() || roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both customer name and room number!");
            return;
        }

        Booking booking = new Booking(name, roomNumber);
        bookings.add(booking);
        JOptionPane.showMessageDialog(this, "Room " + roomNumber + " booked successfully!");

        txtName.setText("");
        txtRoomNumber.setText("");
    }

    private void viewBookings() {
        if (bookings.isEmpty()) {
            txtAreaBookings.setText("No bookings available.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Bookings:\n\n");

            for (Booking booking : bookings) {
                sb.append("Name: ").append(booking.getCustomerName())
                  .append(", Room: ").append(booking.getRoomNumber()).append("\n");
            }

            txtAreaBookings.setText(sb.toString());
        }
    }

    private void checkoutRoom() {
        String roomNumber = txtRoomNumber.getText();

        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter room number for checkout!");
            return;
        }

        for (Booking booking : bookings) {
            if (booking.getRoomNumber().equals(roomNumber)) {
                bookings.remove(booking);
                JOptionPane.showMessageDialog(this, "Room " + roomNumber + " checked out successfully!");
                txtRoomNumber.setText("");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No booking found for room number " + roomNumber);
    }

    public static void main(String[] args) {
        new HotelManagementApp();
    }
}

class Booking {
    private String customerName;
    private String roomNumber;

    public Booking(String customerName, String roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
