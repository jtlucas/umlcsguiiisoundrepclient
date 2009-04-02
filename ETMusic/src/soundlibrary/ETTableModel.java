/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soundlibrary;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * A DefaultTableModel that does not all the editing of rows or columns.
 * @author Jesse edited by Elizabeth Tran
 */
public class ETTableModel extends DefaultTableModel {
  
  /** this keeps NetBeans happy */
  static final long serialVersionUID = 0;

  /**
   * Default constructor required to compile.
   */
  ETTableModel() {
    super();
  }

  /**
   * Two-parameter constructor required by a previous application
   * @param obj data to initialize table
   * @param str array of column names
   */
  ETTableModel(java.lang.Object[][] obj, java.lang.String[] str) {
    super(obj, str);
  }
  
  /**
   * Two-parameter constructor required by this sound application
   * @param data contains the data for the table
   * @param columnNames contains the column names of the table
   */
  ETTableModel(Vector data, Vector columnNames) {
      super(data, columnNames);
  }

  /**
   * We override this method to prevent the cells from being editable.
   * @param row required because we're overriding a method, but not used
   * @param col required because we're overriding a method, but not used
   * @return false to inhibit editing
   */
  @Override
  public boolean isCellEditable( int row, int col ) {
    return false ;
  }
}
