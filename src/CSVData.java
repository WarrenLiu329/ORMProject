import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/***
 * A class that is able to read the data from the sensors in our smartphones
 * 
 * @author Warren
 *
 */
public class CSVData {
	private String[] columnHeaders;
	private double[][] data;

	public CSVData(String filepath, String[] columnNames, int startRow) {
		String dataString = readFileAsString(filepath);
		String[] lines = dataString.split("\n");

		// number of data points
		int n = lines.length - startRow;
		int numColumns = columnNames.length;

		// create storage for column names
		this.columnHeaders = columnNames;

		// create storage for data
		this.data = new double[n][numColumns];
		for (int i = 0; i < lines.length - startRow; i++) {
			String line = lines[startRow + i];
			String[] coords = line.split(",");
			for (int j = 0; j < numColumns; j++) {
				if (coords[j].endsWith("#"))
					coords[j] = coords[j].substring(0, coords[j].length() - 1);
				double val = Double.parseDouble(coords[j]);
				data[i][j] = val;
			}
		}
		
		System.out.println(data.length + " " + data[0].length);
	}

	public static CSVData readCSVFile(String filePath, int numLinesToIgnore, String[] columnNames) {
		return null;
	}

	private String readFileAsString(String filepath) {
		StringBuilder output = new StringBuilder();

		try (Scanner scanner = new Scanner(new File(filepath))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				output.append(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	/***
	 * Returns a new CSVData object for a file ignoring lines at the top. It
	 * uses the first row as the column names. All other data is stored as
	 * doubles.
	 * 
	 * @param fileName
	 * @param numLinestoIgnore
	 * @return
	 */
	public CSVData readCSVFile(String fileName, int numLinestoIgnore) {
		return null;
	}

	/****
	 * returns a row
	 * 
	 * @param r
	 * @return the row we r returning
	 */

	public double[] getRow(int r) {
		return this.data[r];
	}

	/***
	 * returns a column
	 * 
	 * @param Index
	 * @return the column we r returning
	 */
	public double[] getColumn(int colIndex) {
		double[] cols = new double[this.data.length];
		for (int r = 0; r < this.data.length; r++) {
			cols[r] = this.data[r][colIndex];
		}

		return cols;
	}

	/***
	 * return the index of the column based on name
	 * 
	 * @param name
	 * @return the index of the returned name
	 */
	public int getColumnIndex(String name) {
		for (int i = 0; i < this.columnHeaders.length; i++) {
			if (this.columnHeaders[i].equals(name)) {
				return i;
			}
		}
		return 0;
	}

	/***
	 * returns multiple rows
	 * 
	 * @param rowIndexes
	 * @return the rows indicated
	 */
	public double[][] getMultipleRows(int[] rowIndexes) {
		double[][] rowValues = new double[rowIndexes.length][data[0].length];
		for (int i = 0; i < rowIndexes.length; i++) {
			rowValues[i] = getRow(rowIndexes[i]);
			System.out.println("Row: " + i);
		}
		return rowValues;
	}

	/****
	 * returns multiple columns
	 * 
	 * @param columnNames
	 * @return the set of columns user wants
	 */
	public double[][] getMultipleColumns(String[] columnNames) {
		double[] newData = new double[data.length];
		double[][] colValues = new double[data.length][columnNames.length];
		int index = 0;
		for (int i = 0; i < columnNames.length; i++) {
			index = getColumnIndex(columnNames[i]);
			newData = getColumn(index);
			System.out.println("Columns: " + i);
			for (int row = 0; row < data.length; row++) {
				colValues[row][i] = newData[row];
			}
		}

		return colValues;

	}

	/***
	 * returns the data
	 * 
	 * @return the current data
	 */
	public double[][] getData() {
		return this.data;
	}

	/***
	 * returns the list of column names
	 * 
	 * @return the current column names
	 */
	public String[] getColumnTitles() {
		return this.columnHeaders;
	}

	/***
	 * sets the new data value to a spot on the 2d array
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @param newValue
	 */
	public void setValue(int rowIndex, int colIndex, double newValue) {
		data[rowIndex][colIndex] = newValue;
	}

	/***
	 * sets the name of a column
	 * 
	 * @param columnIndex
	 * @param name
	 */
	public void setColumnName(int columnIndex, String name) {
		columnHeaders[columnIndex] = name;
	}

	/***
	 * formats the data into a readable file
	 * 
	 * @param fileName
	 */

	public void saveToFile(String fileName) {

	}
	
	

}
