package org.opennms.features.reporting.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Report Configuration for local reports
 * 
 * @version $Revision$ $Date$
 */
@XmlRootElement(name = "local-reports")
public class LocalReports {

    private List<Report> m_reportList = new ArrayList<Report>();

    @XmlElement(name = "report")
    public List<Report> getReportList() {
        return m_reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.m_reportList = reportList;
    }
}