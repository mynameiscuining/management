package cn.njyazheng.generator;

public class GeneratParam {
    private String tableName;
    private String daoPkg;
    private String domainPkg;
    private String projectName;
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public String getDaoPkg() {
        return daoPkg;
    }
    
    public void setDaoPkg(String daoPkg) {
        this.daoPkg = daoPkg;
    }
    
    public String getDomainPkg() {
        return domainPkg;
    }
    
    public void setDomainPkg(String domainPkg) {
        this.domainPkg = domainPkg;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    
    @Override
    public String toString() {
        return "GeneratParam{" +
                "tableName='" + tableName + '\'' +
                ", daoPkg='" + daoPkg + '\'' +
                ", domainPkg='" + domainPkg + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
