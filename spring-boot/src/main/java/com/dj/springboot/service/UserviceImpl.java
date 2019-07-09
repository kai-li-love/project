package com.dj.springboot.service;

import com.dj.springboot.common.DateUtil;
import com.dj.springboot.common.PoiUtil;
import com.dj.springboot.common.WriteExcelDataDelegated;
import com.dj.springboot.mapper.UserMapper;
import com.dj.springboot.pojo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserviceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;


    @Override
    public void export(UserVO userVO, HttpServletResponse response) throws Exception {

        // 总记录数
        Integer totalRowCount = this.userMapper.selectUserVOCount(userVO);

        // 导出EXCEL文件名称
        String filaName = "用户EXCEL";

        // 标题
        String[] titles = {"账号", "密码", "状态", "昵称", "职位", "手机号", "邮箱", "创建人ID", "创建时间", "修改人ID", "修改时间"};

        // 开始导入
        PoiUtil.exportExcelToWebsite(response, totalRowCount, filaName, titles, new WriteExcelDataDelegated() {
            @Override
            public void writeExcelData(SXSSFSheet eachSheet, Integer startRowCount, Integer endRowCount, Integer currentPage, Integer pageSize) throws Exception {

                PageHelper.startPage(currentPage, pageSize);
                List<UserVO> userVOList = userMapper.selectUserVOList(userVO);

                if (!CollectionUtils.isEmpty(userVOList)) {

                    // --------------   这一块变量照着抄就行  强迫症 起来后期也封装     ----------------------
                    for (int i = startRowCount; i <= endRowCount; i++) {
                        SXSSFRow eachDataRow = eachSheet.createRow(i);
                        if ((i - startRowCount) < userVOList.size()) {

                            UserVO eachUserVO = userVOList.get(i - startRowCount);
                            // ---------   这一块变量照着抄就行  强迫症 后期也封装起来     -----------------------

                            eachDataRow.createCell(0).setCellValue(eachUserVO.getUsername() == null ? "" : eachUserVO.getUsername());
                            eachDataRow.createCell(1).setCellValue(eachUserVO.getPassword() == null ? "" : eachUserVO.getPassword());
                            eachDataRow.createCell(2).setCellValue(eachUserVO.getUserState() == null ? "" : (eachUserVO.getUserState() == 1 ? "启用" : "停用"));
                            eachDataRow.createCell(3).setCellValue(eachUserVO.getNickname() == null ? "" : eachUserVO.getNickname());
                            eachDataRow.createCell(4).setCellValue(eachUserVO.getPosition() == null ? "" : eachUserVO.getPosition());
                            eachDataRow.createCell(5).setCellValue(eachUserVO.getMobile() == null ? "" : eachUserVO.getMobile());
                            eachDataRow.createCell(6).setCellValue(eachUserVO.getEmail() == null ? "" : eachUserVO.getEmail());
                            if (null != eachUserVO.getCreateUid()) {
                                eachDataRow.createCell(7).setCellValue(eachUserVO.getCreateUid());
                            }
                            if (null != eachUserVO.getCreateTime()) {
                                eachDataRow.createCell(8).setCellValue(DateUtil.formatDate(eachUserVO.getCreateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
                            }
                            if (null != eachUserVO.getUpdateUid()) {
                                eachDataRow.createCell(9).setCellValue(eachUserVO.getUpdateUid());
                            }
                            if (null != eachUserVO.getUpdateTime()) {
                                eachDataRow.createCell(10).setCellValue(DateUtil.formatDate(eachUserVO.getUpdateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
                            }
                        }
                    }
                }

            }
        });

        System.out.println("excel导出成功");
    }


}
