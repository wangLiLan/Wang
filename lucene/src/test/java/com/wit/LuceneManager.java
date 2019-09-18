package com.wit;


import com.wit.pojo.Job;
import com.wit.service.JobServiceImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
//这是springboot做junit测试的固定写法
@SpringBootTest
public class LuceneManager {


    @Autowired
    private JobServiceImpl jobService;


    @Test
    public void testCreateIndex() throws IOException {
        //1.指定索引文件的存储位置
        FSDirectory directory = FSDirectory.open(new File("d:\\work\\index"));
        //2.配置版本和分词器
        Analyzer analyzer = new StandardAnalyzer();//标准分词器
        IndexWriterConfig index = new IndexWriterConfig(Version.LATEST, analyzer);
        //3.创建一个用来创建索引的对象
        IndexWriter indexWriter = new IndexWriter(directory, index);
        //创建新的索引前 先删除上次生成的索引
        indexWriter.deleteAll();

        //4.获取原始数据
        List<Job> list = jobService.findAll();
        //有多少的数据就应该构建多少的lucene的文档对象document
        for (Job job : list) {
            Document document = new Document();
            document.add(new LongField("id",job.getId(), Field.Store.YES));
            document.add(new TextField("companyName",job.getCompanyName(), Field.Store.YES));
            document.add(new TextField("companyAddr",job.getCompanyAddr(), Field.Store.YES));
            document.add(new TextField("jobName",job.getJobName(), Field.Store.YES));
            document.add(new TextField("jobAddr",job.getJobAddr(), Field.Store.YES));
            document.add(new IntField("salary",job.getSalary(), Field.Store.YES));
            document.add(new StringField("url",job.getUrl(), Field.Store.YES));
            indexWriter.addDocument(document);
        }
        //关闭资源
        indexWriter.close();
    }
}
