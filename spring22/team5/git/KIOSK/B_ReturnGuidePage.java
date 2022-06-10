package TeamProject;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class B_ReturnGuidePage extends JPanel
{
    private JTextArea returnguide;
    private String termsline;
    private String termstxt;

    public B_ReturnGuidePage()
    {
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(2, 1));

        centerBoldLabel title = new centerBoldLabel("반납 안내", 42);

        Scanner filereader = null;
        try
        {
            filereader = new Scanner(new FileInputStream("./data/ReturnGuide.txt"));
            termstxt = filereader.nextLine() + "\n";
            while (filereader.hasNext())
            {
                termsline = filereader.nextLine() + "\n";//
                termstxt += termsline;
            }
            filereader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        returnguide = new JTextArea(10, 35);
        returnguide.setText(termstxt);
        returnguide.setFont(new Font("IM혜민 regular", Font.PLAIN, 20));
        returnguide.setBackground(Color.WHITE);
        returnguide.setLineWrap(true);

        JScrollPane scrolledText = new JScrollPane(returnguide);
        scrolledText
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel WrapperPanel = new JPanel(new GridLayout(2, 1));
        WrapperPanel.setPreferredSize(new Dimension(500, 550));
        WrapperPanel.setBackground(PasswordMain.BACKGROUND_COLOR);

        WrapperPanel.add(title);
        WrapperPanel.add(scrolledText);

        add(WrapperPanel);

    }
}
