
public interface TableModelElementInfo {
    String getElementName(int column);

    int getElementSize();

    boolean canEditable(int column);
}
