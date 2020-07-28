
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

interface ODESystem {
    String getName();
    
    Arg getArg();
    Function get_f1();
    Function get_f2();

    double F1(double t, double f1, double f2);
    double F2(double t, double f1, double f2);
    
    JDialog getParametersDialog();
}

// Additional data structures
interface Arg {
    String getLetter();
    double getInitValue();
    double getBoundValue();
}
// End of additional data structures

// === http://www.mathprofi.ru/sistemy_differencialnyh_uravnenij.html (example 1) ===
class System1 implements ODESystem {
    @Override
    public String getName() {
        return "x' = -2x + 4y; y' = -x + 3y";
    }
    
    @Override
    public Arg getArg() {
        return new Arg() {
            @Override
            public String getLetter() {
                return "t";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double getBoundValue() {
                return 2.0;
            }
        };
    }

    @Override
    public Function get_f1() {
        return new Function() {
            @Override
            public String getName() {
                return "4exp(-t)-exp(2t)";
            }

            @Override
            public String getLetter() {
                return "x";
            }

            @Override
            public double getInitValue() {
                return 3.0;
            }

            @Override
            public double f(double x) {
                return 4 * Math.exp(-x) - Math.exp(2*x);
            }
        };
    }

    @Override
    public Function get_f2() {
        return new Function() {
            @Override
            public String getName() {
                return "exp(-t)-exp(2t)";
            }

            @Override
            public String getLetter() {
                return "y";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double f(double x) {
                return Math.exp(-x) - Math.exp(2*x);
            }
        };
    }
    
    @Override
    public double F1(double t, double u, double v) {
        return -2 * u + 4 * v;
    }

    @Override
    public double F2(double t, double u, double v) {
        return -u + 3 * v;
    }

    @Override
    public JDialog getParametersDialog() {
        return null;
    }
}

// === http://www.mathprofi.ru/sistemy_differencialnyh_uravnenij.html (example 2) ===
class System2 implements ODESystem {
    @Override
    public String getName() {
        return "x' = 3x - y; y' = 4x - y";
    }
    
    @Override
    public Arg getArg() {
        return new Arg() {
            @Override
            public String getLetter() {
                return "t";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double getBoundValue() {
                return 0.5;
            }
        };
    }
    
    @Override
    public Function get_f1() {
        return new Function() {
            @Override
            public String getName() {
                return "(5+2t)exp(t)";
            }

            @Override
            public String getLetter() {
                return "x";
            }

            @Override
            public double getInitValue() {
                return 5.0;
            }

            @Override
            public double f(double x) {
                return (5 + 2*x) * Math.exp(x);
            }
        };
    }

    @Override
    public Function get_f2() {
        return new Function() {
            @Override
            public String getName() {
                return "(8+4t)exp(t)";
            }

            @Override
            public String getLetter() {
                return "y";
            }

            @Override
            public double getInitValue() {
                return 8.0;
            }

            @Override
            public double f(double x) {
                return (8 + 4*x) * Math.exp(x);
            }
        };
    }
    
    @Override
    public double F1(double t, double u, double v) {
        return 3 * u - v;
    }

    @Override
    public double F2(double t, double u, double v) {
        return 4 * u - v;
    }
    
    @Override
    public JDialog getParametersDialog() {
        return null;
    }
}

// === http://www.mathprofi.ru/sistemy_differencialnyh_uravnenij.html (example 4) ===
class System3 implements ODESystem {
    @Override
    public String getName() {
        return "x' = x - 2y; y' = x - y - 2";
    }
    
    @Override
    public Arg getArg() {
        return new Arg() {
            @Override
            public String getLetter() {
                return "t";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double getBoundValue() {
                return 20.0;
            }
        };
    }
    
    @Override
    public Function get_f1() {
        return new Function() {
            @Override
            public String getName() {
                return "-3cos(t)+5sin(t)+4";
            }

            @Override
            public String getLetter() {
                return "x";
            }

            @Override
            public double getInitValue() {
                return 1.0;
            }

            @Override
            public double f(double x) {
                return -3*Math.cos(x) + 5*Math.sin(x) + 4;
            }
        };
    }

    @Override
    public Function get_f2() {
        return new Function() {
            @Override
            public String getName() {
                return "-4cos(t)+sin(t)+2";
            }

            @Override
            public String getLetter() {
                return "y";
            }

            @Override
            public double getInitValue() {
                return -2.0;
            }

            @Override
            public double f(double x) {
                return -4*Math.cos(x) + Math.sin(x) + 2;
            }
        };
    }
    
    @Override
    public double F1(double t, double u, double v) {
        return u - 2 * v;
    }

    @Override
    public double F2(double t, double u, double v) {
        return u - v - 2;
    }
    
    @Override
    public JDialog getParametersDialog() {
        return null;
    }
}

// === http://edu.sernam.ru/book_p_math2.php?id=31 ===
class System4 implements ODESystem {
    @Override
    public String getName() {
        return "y' = y + z + x; z' = -4y - 3z + 2x";
    }
    
    @Override
    public Arg getArg() {
        return new Arg() {
            @Override
            public String getLetter() {
                return "x";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double getBoundValue() {
                return 5.0;
            }
        };
    }
    
    @Override
    public Function get_f1() {
        return new Function() {
            @Override
            public String getName() {
                return "(10+6x)exp(-x)+5x-9";
            }

            @Override
            public String getLetter() {
                return "y";
            }

            @Override
            public double getInitValue() {
                return 1.0;
            }

            @Override
            public double f(double x) {
                return (10+6*x) * Math.exp(-x) + 5*x - 9;
            }
        };
    }

    @Override
    public Function get_f2() {
        return new Function() {
            @Override
            public String getName() {
                return "(-14-12t)exp(-t)-6t+14";
            }

            @Override
            public String getLetter() {
                return "z";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double f(double x) {
                return (-14-12*x) * Math.exp(-x) - 6*x + 14;
            }
        };
    }
    
    @Override
    public double F1(double t, double u, double v) {
        return u + v + t;
    }

    @Override
    public double F2(double t, double u, double v) {
        return -4 * u - 3 * v + 2 * t;
    }
    
    @Override
    public JDialog getParametersDialog() {
        return null;
    }
}

// === Lab system ===
class SystemFromJob implements ODESystem {
    // Initial parameters
    private double w1 = 100, Sfe1 = 5e-4, lFe1 = 0.5;
    private double w2 = 15, Sfe2 = 5e-4, lFe2 = 0.5;
    private double R = 5, R1 = 100, R2 = 100, E = 10;
    private double a1 = 75, a2 = 1.238348, a3 = 0.0164434908;           // a1 - changed. From the conditions a1 = 3.5714e-5
    
    // Dialog for set the parameters
    class ParametersDialog extends JDialog {
        private final JPanel contentPanel = new JPanel();
        private ArrayList<JLabel> labels = new ArrayList<>();
        private ArrayList<JTextField> edits = new ArrayList<>();

        private JTextField edit_w1, edit_w2, edit_Sfe1, edit_Sfe2, edit_Lfe1, edit_Lfe2, edit_E, edit_R, edit_R1, edit_R2, edit_a1, edit_a2, edit_a3;

        ParametersDialog() {
            setTitle("System parameters:");
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 350, 270);
            getContentPane().setLayout(null);
            contentPanel.setBounds(0, 0, 340, 180);
            contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            getContentPane().add(contentPanel);
            contentPanel.setLayout(null);

            labels.add(new JLabel("w1:"));
            labels.add(new JLabel("w2:"));
            labels.add(new JLabel("Sfe1:"));
            labels.add(new JLabel("Sfe2:"));
            labels.add(new JLabel("Lfe1:"));
            labels.add(new JLabel("Lfe2:"));
            labels.add(new JLabel("E:"));
            labels.add(new JLabel("R:"));
            labels.add(new JLabel("R1:"));
            labels.add(new JLabel("R2:"));
            labels.add(new JLabel("a1:"));
            labels.add(new JLabel("a2:"));
            labels.add(new JLabel("a3:"));

            edits.add(edit_w1 = new JTextField());
            edits.add(edit_w2 = new JTextField());
            edits.add(edit_Sfe1 = new JTextField());
            edits.add(edit_Sfe2 = new JTextField());
            edits.add(edit_Lfe1 = new JTextField());
            edits.add(edit_Lfe2 = new JTextField());
            edits.add(edit_E = new JTextField());
            edits.add(edit_R = new JTextField());
            edits.add(edit_R1 = new JTextField());
            edits.add(edit_R2 = new JTextField());
            edits.add(edit_a1 = new JTextField());
            edits.add(edit_a2 = new JTextField());
            edits.add(edit_a3 = new JTextField());

            int rows = 7;
            for (int i = 0; i < labels.size(); i++) {
                addOneField(labels.get(i), edits.get(i), (i / rows) * 160 + 10, (i % rows) * 25 + 5);
            }

            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 185, 340, 45);
            getContentPane().add(buttonPane);
            buttonPane.setLayout(null);

            JButton okButton = new JButton("OK");
            okButton.setBounds(55, 5, 70, 25);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveValues();
                    dispose();
                }
            });
            buttonPane.add(okButton);

            getRootPane().setDefaultButton(okButton);
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(130, 5, 70, 25);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            buttonPane.add(cancelButton);
            
            initFileds();
	}
        
        // Place one label & one edit field on dialog content panel
        private void addOneField(JLabel lbl, JTextField edit, int x, int y) {
            int w = 30, h = 25;
            lbl.setBounds(x, y, w, h);
            edit.setBounds(x + w + 5, y, w + 80, h);
            edit.setHorizontalAlignment(SwingConstants.RIGHT);
            ((AbstractDocument) edit.getDocument()).setDocumentFilter(new MyDocumentFilter());
            contentPanel.add(lbl);
            contentPanel.add(edit);
        }
        
        // set initial values
        private void initFileds() {
            edit_w1.setText("" + w1);
            edit_w2.setText("" + w2);
            edit_Sfe1.setText("" + Sfe1);
            edit_Sfe2.setText("" + Sfe2);
            edit_Lfe1.setText("" + lFe1);
            edit_Lfe2.setText("" + lFe2);
            edit_E.setText("" + E);
            edit_R.setText("" + R);
            edit_R1.setText("" + R1);
            edit_R2.setText("" + R2);
            edit_a1.setText("" + a1);
            edit_a2.setText("" + a2);
            edit_a3.setText("" + a3);
        }
        
        // save values
        private void saveValues() {
            try {
                w1 = Double.parseDouble(edit_w1.getText());
                w2 = Double.parseDouble(edit_w2.getText());
                Sfe1 = Double.parseDouble(edit_Sfe1.getText());
                Sfe2 = Double.parseDouble(edit_Sfe2.getText());
                lFe1 = Double.parseDouble(edit_Lfe1.getText());
                lFe2 = Double.parseDouble(edit_Lfe2.getText());
                E = Double.parseDouble(edit_E.getText());
                R = Double.parseDouble(edit_R.getText());
                R1 = Double.parseDouble(edit_R1.getText());
                R2 = Double.parseDouble(edit_R2.getText());
                a1 = Double.parseDouble(edit_a1.getText());
                a2 = Double.parseDouble(edit_a2.getText());
                a3 = Double.parseDouble(edit_a3.getText());
            } catch (NumberFormatException e) {
            }
        }
    }
    
    @Override
    public Arg getArg() {
        return new Arg() {
            @Override
            public String getLetter() {
                return "t";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double getBoundValue() {
                return 0.02;
            }
        };
    }
    
    @Override
    public Function get_f1() {
        return new Function() {
            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getLetter() {
                return "i1";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double f(double x) {
                return 0;
            }
        };
    }

    @Override
    public Function get_f2() {
        return new Function() {
            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getLetter() {
                return "i2";
            }

            @Override
            public double getInitValue() {
                return 0.0;
            }

            @Override
            public double f(double x) {
                return 0;
            }
        };
    }
    
    @Override
    public String getName() {
        return "i1' = (E - (R+R1)i1 - R*i2) / L1; i2' = (E - (R+R2)i2 - R*i1) / L2";
    }
    
    private double mju(double H) {
        return a1 + a2 * a3 / ( 1 + a3*H*a3*H );
    }
    
    @Override
    public double F1(double t, double u, double v) {
        double H1 = u * w1 / lFe1;
        double L1 = w1 * w1 * Sfe1 * mju(H1) / lFe1;
        return (E - (R + R1) * u - R * v) / L1;
    }

    @Override
    public double F2(double t, double u, double v) {
        double H2 = v * w2 / lFe2;
        double L2 = w2 * w2 * Sfe2 * mju(H2) / lFe2;
        return (E - (R + R2) * v - R * u) / L2;
    }
    
    @Override
    public JDialog getParametersDialog() {
        return new ParametersDialog();
    }
}