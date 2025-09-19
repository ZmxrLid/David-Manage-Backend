package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.*;
import com.zmxrlid.backend.mapper.JixiaoremakeMapper;
import com.zmxrlid.backend.mapper.KaoqinMapper;
import com.zmxrlid.backend.mapper.SalaryMapper;
import com.zmxrlid.backend.mapper.TeacherMapper;
import com.zmxrlid.backend.service.ILatentService;
import com.zmxrlid.backend.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxrlid.backend.service.IShouzhiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {


    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private KaoqinMapper kaoqinMapper;

    @Resource
    private JixiaoremakeMapper jixiaoremakeService;

    @Resource
    private ILatentService latentService;

    @Resource
    private ISalaryService salaryService;

    @Resource
    private IShouzhiService shouzhiService;

    @Override
    public Salary getnumbyteacherid(Integer teacherId, String date) {
        return salaryMapper.getnumbyteacherid(teacherId,date);
    }

    @Override
    public Salary fandnumbyteacherid(Integer teacherId, String date) {
        return salaryMapper.fandnumbyteacherid(teacherId,date);
    }

    @Override
    public Salary getforteacher(Integer teacherId, String date) {
        return salaryMapper.getforteacher(teacherId,date);
    }

    @Override
    public Boolean chuangjian(Integer teacherId, String date) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        Kaoqin kaoqin = kaoqinMapper.getforhave(teacherId,date);
        Jixiaoremake jixiaoremake = jixiaoremakeService.getforteacher(teacherId,date);
        Salary salary = new Salary();
        salary.setSalaryMasterid(teacher.getTeacherMasterid());
        salary.setSalaryTeacherid(teacherId);
        salary.setSalaryTeachername(teacher.getTeacherName());
        salary.setSalaryDixin(teacher.getTeacherNum());
        salary.setSalaryKaoqin(kaoqin.getKaoqinNum());
        salary.setSalaryExtra(0.00);
        salary.setSalaryGufen(0.00);
        salary.setSalaryState(0);
        salary.setSalaryDate(date);
        //绩效奖金
        Double jixiao = jixiaoremake.getJixiaoremakeJixiao();
        salary.setSalaryJixiao(jixiao);
        switch (teacher.getTeacherJixiao()) {
            case 1:
                if (salary.getSalaryJixiao()<=5000){
                    salary.setSalaryJixiaonum(0.00);
                } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.02);
                } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=7000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.03);
                } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.04);
                } else if (salary.getSalaryJixiao()>8000){
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.05);
                }
                // 当expression等于value1时执行
                break;
            case 2:
                if (salary.getSalaryJixiao()<=4000){
                    salary.setSalaryJixiaonum(0.00);
                } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=6000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.05);
                } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=8000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.1);
                } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=10000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.15);
                } else if (salary.getSalaryJixiao()>10000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.2);
                }
                // 当expression等于value2时执行
                break;
            case 3:
                if (salary.getSalaryJixiao()<=3000){
                    salary.setSalaryJixiaonum(0.00);
                } else if (salary.getSalaryJixiao()>3000&&salary.getSalaryJixiao()<=4000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.08);
                } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=5000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.12);
                } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.16);
                } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=7000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.19);
                } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.21);
                } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=10000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.26);
                } else if (salary.getSalaryJixiao()>10000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.26);
                }
                // 当expression等于value2时执行
                break;
            case 4:
                if (salary.getSalaryJixiao()<=2000){
                    salary.setSalaryJixiaonum(0.00);
                } else if (salary.getSalaryJixiao()>2000&&salary.getSalaryJixiao()<=3000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.03);
                } else if (salary.getSalaryJixiao()>3000&&salary.getSalaryJixiao()<=4000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.10);
                } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=5000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.13);
                } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.15);
                } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.16);
                } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=9000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.19);
                } else if (salary.getSalaryJixiao()>9000) {
                    salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.21);
                }
                // 当expression等于value2时执行
                break;
            default:
                salary.setSalaryJixiaonum(0.00);
                // 当expression不匹配任何case时执行
                break;
        }

        // 工龄计算 - 返回整数类型的工龄（单位：年）
        int gongling = 0;
        try {
            if (teacher.getTeacherTime() != null && !teacher.getTeacherTime().isEmpty()) {
                // 使用对应格式的 SimpleDateFormat
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date entryDate = sdf.parse(teacher.getTeacherTime());
                Date currentDate = new Date();

                // 计算整数年份差
                long diffInMillis = currentDate.getTime() - entryDate.getTime();
                // 转换为年数并取整
                gongling = (int) (diffInMillis / (1000L * 60 * 60 * 24 * 365));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 日期解析失败，工龄保持为0
        }
        salary.setSalaryGongling(gongling);
        if (gongling*50>=300){
            salary.setSalaryGongsalary(300.0);
        }else {
            salary.setSalaryGongsalary(gongling*50.00);
        }

        //保险
        if (teacher.getTeacherBaostate()==1){
            salary.setSalaryBaoxian(teacher.getTeacherBaogong()-teacher.getTeacherBaonum());
        }else {
            salary.setSalaryBaoxian(0.00);
        }

        //邀约提成
        Double yaoyue = 0.00;
        String datemonth = date+"%";
        if (latentService.fromname(teacher.getTeacherName(), datemonth) != null){
            List<Latent> latentList = latentService.fromname(teacher.getTeacherName(), datemonth);
            for (Latent latent : latentList) {
                yaoyue += latent.getLatentNum() * 0.3;
            }
        }
        if (latentService.telname(teacher.getTeacherName(), datemonth) != null){
            List<Latent> latentList = latentService.telname(teacher.getTeacherName(), datemonth);
            for (Latent latent : latentList) {
                yaoyue += latent.getLatentNum() * 0.1;
            }
        }
        if (latentService.teachername(teacher.getTeacherName(), datemonth) != null){
            List<Latent> latentList = latentService.teachername(teacher.getTeacherName(), datemonth);
            for (Latent latent : latentList) {
                yaoyue += latent.getLatentNum() * 0.1;
            }
        }
        if (latentService.talkname(teacher.getTeacherName(), datemonth) != null){
            List<Latent> latentList = latentService.talkname(teacher.getTeacherName(), datemonth);
            for (Latent latent : latentList) {
                yaoyue += latent.getLatentNum() * 0.15;
            }
        }
        if (latentService.speech(teacher.getTeacherName(), datemonth) != null){
            List<Latent> latentList = latentService.speech(teacher.getTeacherName(), datemonth);
            for (Latent latent : latentList) {
                yaoyue += latent.getLatentNum() * 0.1;
            }
        }
        salary.setSalaryTicheng(yaoyue);

        // 应发工资
        salary.setSalaryNum(salary.getSalaryDixin()+
                salary.getSalaryKaoqin()+
                salary.getSalaryJixiaonum()+
                salary.getSalaryTicheng()+
                salary.getSalaryBaoxian()+
                salary.getSalaryGongsalary()+
                salary.getSalaryExtra());

        // 实发工资
        salary.setSalaryEndnum(salary.getSalaryNum()+salary.getSalaryGufen());
        return salaryService.saveOrUpdate(salary);
    }

    @Override
    public Boolean shuaxin(Salary salary) {
        if (salary.getSalaryState()==1){
            return false;
        }else {
            Teacher teacher = teacherMapper.selectById(salary.getSalaryTeacherid());
            Kaoqin kaoqin = kaoqinMapper.getforhave(salary.getSalaryTeacherid(),salary.getSalaryDate());
            Jixiaoremake jixiaoremake = jixiaoremakeService.getforteacher(salary.getSalaryTeacherid(),salary.getSalaryDate());
            //绩效奖金
            Double jixiao = jixiaoremake.getJixiaoremakeJixiao();
            salary.setSalaryJixiao(jixiao);
            switch (teacher.getTeacherJixiao()) {
                case 1:
                    if (salary.getSalaryJixiao()<=5000){
                        salary.setSalaryJixiaonum(0.00);
                    } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.02);
                    } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=7000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.03);
                    } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.04);
                    } else if (salary.getSalaryJixiao()>8000){
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.05);
                    }
                    // 当expression等于value1时执行
                    break;
                case 2:
                    if (salary.getSalaryJixiao()<=4000){
                        salary.setSalaryJixiaonum(0.00);
                    } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=6000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.05);
                    } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=8000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.1);
                    } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=10000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.15);
                    } else if (salary.getSalaryJixiao()>10000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.2);
                    }
                    // 当expression等于value2时执行
                    break;
                case 3:
                    if (salary.getSalaryJixiao()<=3000){
                        salary.setSalaryJixiaonum(0.00);
                    } else if (salary.getSalaryJixiao()>3000&&salary.getSalaryJixiao()<=4000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.08);
                    } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=5000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.12);
                    } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.16);
                    } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=7000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.19);
                    } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.21);
                    } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=10000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.26);
                    } else if (salary.getSalaryJixiao()>10000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.26);
                    }
                    // 当expression等于value2时执行
                    break;
                case 4:
                    if (salary.getSalaryJixiao()<=2000){
                        salary.setSalaryJixiaonum(0.00);
                    } else if (salary.getSalaryJixiao()>2000&&salary.getSalaryJixiao()<=3000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.03);
                    } else if (salary.getSalaryJixiao()>3000&&salary.getSalaryJixiao()<=4000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.10);
                    } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=5000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.13);
                    } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.15);
                    } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.16);
                    } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=9000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.19);
                    } else if (salary.getSalaryJixiao()>9000) {
                        salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.21);
                    }
                    // 当expression等于value2时执行
                    break;
                default:
                    salary.setSalaryJixiaonum(0.00);
                    // 当expression不匹配任何case时执行
                    break;
            }
            // 工龄计算 - 返回整数类型的工龄（单位：年）
            int gongling = 0;
            try {
                if (teacher.getTeacherTime() != null && !teacher.getTeacherTime().isEmpty()) {
                    // 使用对应格式的 SimpleDateFormat
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date entryDate = sdf.parse(teacher.getTeacherTime());
                    Date currentDate = new Date();

                    // 计算整数年份差
                    long diffInMillis = currentDate.getTime() - entryDate.getTime();
                    // 转换为年数并取整
                    gongling = (int) (diffInMillis / (1000L * 60 * 60 * 24 * 365));
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // 日期解析失败，工龄保持为0
            }
            salary.setSalaryGongling(gongling);
            if (gongling*50>=300){
                salary.setSalaryGongsalary(300.0);
            }else {
                salary.setSalaryGongsalary(gongling*50.00);
            }
            //保险
            if (teacher.getTeacherBaostate()==1){
                salary.setSalaryBaoxian(teacher.getTeacherBaogong()-teacher.getTeacherBaonum());
            }else {
                salary.setSalaryBaoxian(0.00);
            }
            //邀约提成
            Double yaoyue = 0.00;
            String datemonth = salary.getSalaryDate()+"%";
            if (latentService.fromname(teacher.getTeacherName(), datemonth) != null){
                List<Latent> latentList = latentService.fromname(teacher.getTeacherName(), datemonth);
                for (Latent latent : latentList) {
                    yaoyue += latent.getLatentNum() * 0.3;
                }
            }
            if (latentService.telname(teacher.getTeacherName(), datemonth) != null){
                List<Latent> latentList = latentService.telname(teacher.getTeacherName(), datemonth);
                for (Latent latent : latentList) {
                    yaoyue += latent.getLatentNum() * 0.1;
                }
            }
            if (latentService.teachername(teacher.getTeacherName(), datemonth) != null){
                List<Latent> latentList = latentService.teachername(teacher.getTeacherName(), datemonth);
                for (Latent latent : latentList) {
                    yaoyue += latent.getLatentNum() * 0.1;
                }
            }
            if (latentService.talkname(teacher.getTeacherName(), datemonth) != null){
                List<Latent> latentList = latentService.talkname(teacher.getTeacherName(), datemonth);
                for (Latent latent : latentList) {
                    yaoyue += latent.getLatentNum() * 0.15;
                }
            }
            if (latentService.speech(teacher.getTeacherName(), datemonth) != null){
                List<Latent> latentList = latentService.speech(teacher.getTeacherName(), datemonth);
                for (Latent latent : latentList) {
                    yaoyue += latent.getLatentNum() * 0.1;
                }
            }
            salary.setSalaryTicheng(yaoyue);

            // 股份分红
            if (teacher.getTeacherGu()==null||teacher.getTeacherGu()==0||shouzhiService.getforhave(salary.getSalaryMasterid(),salary.getSalaryDate()).getShouzhiNum()==null){
                salary.setSalaryGufen(0.00);
            }else {
                salary.setSalaryGufen(shouzhiService.getforhave(salary.getSalaryMasterid(),salary.getSalaryDate()).getShouzhiNum()*teacher.getTeacherGu()/100);
            }

            // 应发工资
            salary.setSalaryNum(salary.getSalaryDixin()+salary.getSalaryKaoqin()+salary.getSalaryJixiaonum()+salary.getSalaryTicheng()+salary.getSalaryBaoxian()+salary.getSalaryGongsalary()+salary.getSalaryExtra());

            // 实发工资
            salary.setSalaryEndnum(salary.getSalaryNum()+salary.getSalaryGufen());
            return salaryService.saveOrUpdate(salary);
        }
    }
}
