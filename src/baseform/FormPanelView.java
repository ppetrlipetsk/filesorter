package baseform;

import baseform.baseformtypes.DefineBottomButtons;
import baseform.baseformtypes.IPanelForm;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;


public class FormPanelView extends FormActionedView implements IPanelForm {

    protected JPanel mainPanel;
    protected JPanel bottomPanel;
    protected JPanel dialogPanel;
    protected ArrayList<JButton> bottomButtons;
    protected Dimension buttonsSize;

    // Propertyes

    public Dimension getButtonsSize() {
        return buttonsSize;
    }

    public void setButtonsSize(Dimension buttonsSize) {
        this.buttonsSize = buttonsSize;
    }

    // Метод, возвращает коллекцию кнопок для нижней панели
    // Переопределить в потомках, если нужен другой набор кнопок
    protected ArrayList<JButton> getBottomButtons() {
        return bottomButtons;
    }

    public void setBottomButtons(ArrayList<JButton> bl) {
        bottomButtons=bl;
    }

    public ArrayList<JButton> setBottomButtons(ButtonsList bl) {
        ArrayList<JButton> b =bl.getButtons();
        Dimension buttonsDim= getButtonsDimensions();
        for (JButton bx : bl.getButtons()) {
            bx.setPreferredSize(buttonsDim);
            bx.setMinimumSize(buttonsDim);
        }
        GroupLayout layout=(GroupLayout)(bottomPanel.getLayout());
        bottomButtons=b;
        prepareBottomButtons(layout);
        return b;
    }

    // EOF Propertyes

    // Constructors

    public FormPanelView() {
        super();
    }

    public FormPanelView(DefineBottomButtons b) {
        super();
        if (b==DefineBottomButtons.DEFAULT)
            setBottomButtons(getDefaultBottomButtons());
    }

    public FormPanelView(String title) {
        super(title);
    }

    public FormPanelView(JDialog p, String title, boolean modal){
        super(p,title,modal);
        //initForm();
    }

    public FormPanelView(JDialog p, String t, Dialog.ModalityType m){
        super(p,t,m);
    }

    protected void initForm(){
        super.initForm();
        buttonsSize=setDefButtSize();
        initPanels();
    }

    // EOF Constructor

    // Init methods

    // Задает, заданный по умочанию размер кнопок, если не задан указанный размер при инициализации формы
    protected Dimension setDefButtSize(){
        return new Dimension(80,25);
    }

    // Возвращает заданный по умолчанию набор кнопок
    protected ButtonsList getDefaultBottomButtons() {
        ButtonsList bl = new ButtonsList();
        bl.add(new ButtonItem("okButton", "Ok"));
        bl.add(new ButtonItem("cancelkButton", "Отмена"));
        return bl;
    }

    // Создает и инициализирует главный контейнер формы
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        BorderLayout mainLayout = new BorderLayout();
        panel.setLayout(mainLayout);
        return panel;
    }

    // Диспетчер инициализации нижней панели и панели диалога
    protected void initPanels(){
        BorderLayout mainLayout = new BorderLayout();
        this.getFormContainer().setLayout(mainLayout);

        mainPanel = createMainPanel();
        dialogPanel = (JPanel) initDialogPanel();
        bottomPanel = (JPanel) initBottomPanel();

        this.getFormContainer().add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(dialogPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
    }

    // Инициализирует панель диалога
    protected Container initDialogPanel() {
        JPanel dlgPanel = new JPanel();
        GroupLayout layout = new GroupLayout(dlgPanel);
        dlgPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        prepareElements(layout);
        dlgPanel.setBorder(new EtchedBorder());
        return dlgPanel;
    }

    // Размещает элементы диалога в диалоговой панели
    protected void prepareElements(GroupLayout layout){
    }


    //Возвращает заданный в переменной buttonsSize размер кнопок
    protected Dimension getButtonsDimensions() {
        return buttonsSize;
    }

    // Инициализирует нижнюю панель
    protected   Container initBottomPanel() {
        JPanel bottomPanel2=new JPanel();
        GroupLayout layout = new GroupLayout(bottomPanel2);
        bottomPanel2.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        prepareBottomButtons(layout);
        bottomPanel2.setBorder(new EtchedBorder());
        return bottomPanel2;
    }

    // Строит макет для вывода кнопок нижней панели
    protected void prepareBottomButtons(GroupLayout layout) {
        if (bottomButtons!=null) {
            GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

            int i=0;
            if (bottomButtons.size()==1){
                hGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            }
            for (JButton b : bottomButtons) {
                GroupLayout.Group g = layout.createParallelGroup().addComponent(b);
                hGroup.addGroup(g);
                if (i<(bottomButtons.size()-1))
                hGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
                i++;
            }
            if (bottomButtons.size()==1){
                hGroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            }
            layout.setHorizontalGroup(hGroup);
            GroupLayout.Group vg = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
            for (JButton b : bottomButtons) {
                vg.addComponent(b);
            }
            vGroup.addGroup(vg);
            layout.setVerticalGroup(vGroup);
        }
    }
}