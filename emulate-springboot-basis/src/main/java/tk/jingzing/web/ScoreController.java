package tk.jingzing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.jingzing.dao.IScoreDao;
import tk.jingzing.entity.Score;

import java.util.List;

/**
 * Created by wangyunjing on 16/6/9.
 */
@RestController
@RequestMapping("score")
public class ScoreController {
    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private IScoreDao scoreService;

    @RequestMapping("/scoreList")
    public List<Score> getScoreList(){
        logger.info("从数据库读取Score集合");
        // 测试更新数据库
     //   logger.info("更新的行数：" + scoreService.updateScoreById(88.8f, 2));
     //   scoreService.delete(23);

        return scoreService.getList();
    }
}
