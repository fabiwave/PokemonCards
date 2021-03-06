package Visitors;

/**
 * @author fabiwave
 */

public interface IVisitable {

    /**
     * Makes a class a visitable one by accepting the visitor
     *
     * @param visitor parameter of the visitor that will visit the object
     */
    void acceptVisitor(IVisitor visitor);
}
