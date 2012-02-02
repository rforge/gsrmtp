package org.af.gMCP.config;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GeneralConfig extends SpecificConfig {

	/**
	 * Constructor - use Configuration.getInstance().getGeneralConfig() to access it.
	 * @param conf GeneralConfig object
	 */
    GeneralConfig(Configuration conf) {
        super(conf);        
    }

    public final static String DISABLE = "disable";

    public void setTempDir(String tempDir) {
        setProperty("tempdir", tempDir);
    }

    public String getTempDir() {
        String tmpDir = getProperty("tempdir", "");
        if (tmpDir.trim().equals("")) {
            tmpDir = System.getProperty("java.io.tmpdir");
        }
        return tmpDir;
    }

    public void setPDFViewerPath(String pdfViewerPath) {
        setProperty("acrobat.path", pdfViewerPath);
    }

    public String getPDFViewerPath() {
        return getProperty("acrobat.path", "");
    }

    public void setPDFViewerOptions(String pdfViewerOptions) {
        setProperty("pdfviewer.options", pdfViewerOptions);
    }

    public String getPDFViewerOptions() {
        return getProperty("pdfviewer.options", "");
    }

    public void setFontSize(int i) {
        setIntProperty("font.size", i);
    }

    public int getFontSize() {
        return getIntProperty("font.size", "12");
    }

    public void setGridSize(int grid) {
		setProperty("grid", ""+Math.max(grid, 1));		
	}
    
    public int getGridSize() {
    	int grid = Integer.parseInt(getProperty("grid", "50"));
		return Math.max(1, grid);		
	}

    public void setDigits(int digit) {
		setProperty("Digits", ""+digit);		
		setFormat();
	}
    
    public int getDigits() {
		return Integer.parseInt(getProperty("Digits", "3"));		
	}
    
    DecimalFormat format = null;
    
	public DecimalFormat getDecFormat() {
		if (format==null) {
			setFormat();
		} 
		return format;
	}
    
    private void setFormat() {
    	String s = "#.";
		for (int i=0; i < getDigits(); i++) {
			s = s + "#";
		}
		format = new DecimalFormat(s);		
	}

	public void setLineWidth(int lw) {
		setProperty("linewidth", ""+lw);		
	}
    
    public int getLineWidth() {
		return Integer.parseInt(getProperty("linewidth", "2"));		
	}
    
    public void setEps(double eps) {
		setProperty("epsilon", ""+eps);		
	}
    
    public double getEpsilon() {
		return Double.parseDouble(getProperty("epsilon", "0.001"));		
	}
    
	public boolean showFractions() {		
		return Boolean.parseBoolean(getProperty("showFractions", "true"));
	}
	
	public void setShowFractions(boolean showFractions) {		
		setProperty("showFractions", ""+showFractions);
	}
    
	public boolean getColoredImages() {		
		return Boolean.parseBoolean(getProperty("coloredImages", "true"));
	}
	
	public void setColoredImages(boolean colored) {		
		setProperty("coloredImages", ""+colored);
	}

	public int getDigitsInTables() {
		return Integer.parseInt(getProperty("digits.in.tables", "6"));	
	}

	public boolean showRejected() {
		return Boolean.parseBoolean(getProperty("showRejected", "true"));
	}
	
	public void setShowRejected(boolean showRejected) {		
		setProperty("showRejected", ""+showRejected);
	}

	public boolean useEpsApprox() {
		return Boolean.parseBoolean(getProperty("useEpsApprox", "true"));
	}
	
	public void setUseEpsApprox(boolean useEpsApprox) {
		setProperty("useEpsApprox", ""+useEpsApprox);
	}
	
	public boolean useJLaTeXMath() {
		return Boolean.parseBoolean(getProperty("useJLaTeXMath", "true"));
	}
	
	public void setUseJLaTeXMath(boolean useJLaTeXMath) {
		setProperty("useJLaTeXMath", ""+useJLaTeXMath);
	}
	
	public boolean checkOnline() {
		return Boolean.parseBoolean(getProperty("checkOnline", "true"));
	}
	
	public void setCheckOnline(boolean checkOnline) {
		setProperty("checkOnline", ""+checkOnline);
	}
	
	public boolean tellAboutCheckOnline() {
		return Boolean.parseBoolean(getProperty("tellAboutCheckOnline", "false"));
	}
	
	public void setTellAboutCheckOnline(boolean checkOnline) {
		setProperty("tellAboutCheckOnline", ""+checkOnline);
	}
	
	public boolean reminderNewVersion() {
		return Boolean.parseBoolean(getProperty("reminderNewVersion", "true"));
	}
	
	public void setReminderNewVersion(boolean reminderNewVersion) {
		setProperty("reminderNewVersion", ""+reminderNewVersion);
	}
	
	public void setVersionNumber(String version) {
		setProperty("gMCPversion", version);
	}

	public String getVersionNumber() {
		return getProperty("gMCPversion", "<= 0.6.0");
	}
	
	public void setRVersionNumber(String version) {
		setProperty("Rversion", version);
	}

	public String getRVersionNumber() {
		return getProperty("Rversion", "unknown");
	}

	public void setRandomID() {
		setProperty("randomID", ""+Math.abs((new Random()).nextInt()));
	}

	public String getRandomID() {
		if (getProperty("randomID", "NOT_SET_YET").equals("NOT_SET_YET")) {
			setRandomID();
		}
		return getProperty("randomID");
	}
	
	public List<String> getLatestGraphs() {
		Vector<String> graphs = new Vector<String>(); 
		for (int i=0; i<4; i++) {
			String graph = getProperty("saved_graph_"+i, "NOT_SAVED_YET");
			if (graph.startsWith("R Object") || new File(graph).exists()) {
				graphs.add(graph);
			}
		}
		return graphs;
	}
	
	public void addGraph(String graph) {
		int i=1;
		for (; i<4; i++) {
			if (graph.equals(getProperty("saved_graph_"+(i-1), "NOT_SAVED_YET"))) break; 
		}
		for (i--; i>0; i--) {
			String g = getProperty("saved_graph_"+(i-1), "NOT_SAVED_YET");
			setProperty("saved_graph_"+i, g);			
		}
		setProperty("saved_graph_"+0, graph);		
	}

	public int getNumberOfStarts() {
		return Integer.parseInt(getProperty("NumberOfStarts", "0"));
	}

	public void setNumberOfStarts(int i) {
		setProperty("NumberOfStarts", ""+i);
	}

	public String getTimesSymbol() {
		/* "", "*", "\\cdot", "\\times" */
		return getProperty("getTimesSymbol", "");
	}
	
	public void setTimesSymbol(String s) {
		/* "", "*", "\\cdot", "\\times" */
		setProperty("getTimesSymbol", s);
	}

	public double getAccuracy() {
		return Double.parseDouble(getProperty("fractionAccuracy", "0.000001"));		
	}

	public void setExperimental(boolean b) {
		setProperty("experimentalFeatures", ""+b);
	}
	public boolean experimentalFeatures() {
		return Boolean.parseBoolean(getProperty("experimentalFeatures", "true"));
	}

	public void setVerbose(boolean b) {
		setProperty("verbose", ""+b);
	}
	
	public boolean verbose() {
		return Boolean.parseBoolean(getProperty("verbose", "true"));
	}
	
	public void setVariable(String variable, double value) {
		setProperty("Variable_"+variable, ""+value);		
	}
    
    public double getVariable(String variable) {
		return Double.parseDouble(getProperty("Variable_"+variable, "0.5"));		
	}

	public boolean exportTransparent() {
		return Boolean.parseBoolean(getProperty("exportTransparent", "true"));
	}

	public void setExportTransparent(boolean b) {
		setProperty("exportTransparent", ""+b);
	}

	public boolean getUnAnchor() {
		return Boolean.parseBoolean(getProperty("unAnchor", "true"));
	}
	
	public void setUnAnchor(boolean b) {
		setProperty("unAnchor", ""+b);
	}

	public boolean simplify() {
		return Boolean.parseBoolean(getProperty("simplify", "false"));
	}

	public int getDigits2() {
		return Integer.parseInt(getProperty("digits2", "6"));		
	}

	public void setSimplify(boolean b) {
		setProperty("simplify", ""+b);		
	}

	public void setDigits2(int digits2) {
		setProperty("digits2", ""+digits2);
	}

	public Integer getNumberOfSimulations() {
		return Integer.parseInt(getProperty("numberOfSimulations", "10000"));
	}

	public String getTypeOfRandom() {
		return getProperty("typeOfRandom", "quasirandom");
	}
	
	public void setNumberOfSimulations(int nr) {
		setProperty("numberOfSimulations", ""+nr);
	}
	
	public void setTypeOfRandom(String type) {
		setProperty("typeOfRandom", ""+type);
	}
}
