package cn.njyazheng.generator;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MapperCodeGen;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@ComponentScan(basePackages = {"com.ibeetl.**"})
public class Generator {
    private final static Logger logger = LoggerFactory.getLogger(Generator.class);
    @Autowired
    private SqlManagerFactoryBean sqlManagerFactoryBean;
    
    @Value("${beetlsql.sqlPath: /sql}")
    private String sqlPath;
    
    @GetMapping("/gen")
    public String get(GeneratParam generatParam) throws Exception {
        try {
            if(logger.isDebugEnabled()){
                logger.debug("generator params:{}"+generatParam);
            }
    
            //获取主项目名称
            //用户目录
            String userDir = System.getProperty("user.dir");
            if(userDir==null){
                throw new NullPointerException("用户目录未找到");
            }
            if(!StringUtils.isEmpty(generatParam.getProjectName())) {
                GenKit.setResourcePathRelativeToSrc("..\\" + generatParam.getProjectName() + "\\src\\main\\resources");
                GenKit.setSrcPathRelativeToSrc("..\\" + generatParam.getProjectName() + "\\src\\main\\java");
            }
            File file=new File(GenKit.getJavaResourcePath()+"\\"+sqlPath);
            if(file.isFile()||!file.exists()){
                file.mkdir();
            }
            GenConfig config = new GenConfig();
            MapperCodeGen mapper = new MapperCodeGen(generatParam.getDaoPkg());
            mapper.setMapperTemplate(config.getTemplate("/org/beetl/sql/ext/gen/mapper.btl"));
            config.codeGens.add(mapper);
            SQLManager sqlManager = sqlManagerFactoryBean.getObject();
            sqlManager.genPojoCode(generatParam.getTableName(), generatParam.getDomainPkg(), config);
            sqlManager.genSQLFile(generatParam.getTableName(), config);
            return "generate success";
        } catch (Exception e) {
            logger.error("generate failure",e);
            return "generate failure";
        }
    }
}
