package com.cn.ssm.service.impl;


import java.io.InputStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cn.ssm.service.PoiService;

@Service
public class PoiServiceImpl implements PoiService{
	 private final static String excel2003L =".xls";    //2003- 版本的excel  
     private final static String excel2007U =".xlsx";   //2007+ 版本的excel 
     private final static String [] GOODS_TITLES = {"执行结果","分类","商品名称","参考价格下限","参考价格上限",
    	 "生产时间","生产地址","生产厂家","品牌","库存量","保质期","备注"};
    	 
     private final static String [] ORDER_TITLES = {"执行结果","流水号","商品名","数量","单价","金额","时间"};
     
  /*  @Autowired
 	private MtGoodsMapper goodsMapper;
    @Autowired
	private MtMemberMapper mmbMapper;
	@Autowired
	private MtCategoryMapper categoryMapper;
	@Autowired
    private MtMmbBankAccountMapper mtMmbBankAccountMapper;
	@Autowired
	private MtMmbWarehouseMapper mtMmbWarehouseMapper;
	@Resource
	private MtOrdertitleMapper mtOrdertitleMapper;
	@Resource
	private MtOrderMapper mtOrderMapper;
	@Autowired
	private IOrderService orderService;*/
	
	public Workbook getWorkbook(InputStream inputStream, String fileName) {
		Workbook wb = null;
		try {
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			if (excel2003L.equals(fileType)) {
				wb = new HSSFWorkbook(inputStream); // 2003-
			} else if (excel2007U.equals(fileType)) {
				wb = new XSSFWorkbook(inputStream); // 2007+
			} else {
				return null;
			}
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	*//**
	 * 检查数据标题列名称
	 * @param namesRow
	 * @return
	 *//*
	public boolean checkDataTitle(Row titlesRow,String[] titles){
		try {
			if(titlesRow.getLastCellNum()<titles.length){
				return false;
			}
			for (int i = 0; i < titles.length; i++) {
				Cell cell = titlesRow.getCell(i);
				String name = "";
				if(cell != null){
					name = cell.getStringCellValue();
				}
				if(!titles[i].equals(name)){
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	*//**
	 * 商品导入 
	 *//*
	@Override
	public boolean importGoods(Workbook work,Map<String,Object> returnMap) {
		int total = 0;
		int success = 0;
		try {
			//获取excel中第一个表
			Sheet sheet = work.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			if(lastRowNum>1001){
				returnMap.put("return_message", "执行导入失败:数据不能1000行!");
				return false;
			}
			
			//获取供应商名称
			Row firstRow = sheet.getRow(0);
			Cell titleCell = null;
			if(firstRow != null){
				titleCell = firstRow.getCell(0);
			}
			String titleName = null;
			if(titleCell != null){
				titleName = titleCell.getStringCellValue();
			}
			if(!"供应商".equals(titleName)){
				returnMap.put("return_message", "执行导入失败:数据列不匹配!");
				return false;
			}
			Cell sellerCell = firstRow.getCell(1);
			String sellerName = null;
			if(sellerCell != null){
				sellerName = sellerCell.getStringCellValue();
			}
			if(StringUtils.isBlank(sellerName)){
				returnMap.put("return_message", "执行导入失败:供应商名称不可以为空!");
				return false;
			}
			
			//根据名称查询供应商
			List<MtMember> members = mmbMapper.selectByName(sellerName);
			if(members==null || members.size() != 1){
				returnMap.put("return_message", "执行导入失败:无法匹配到供应商");
				return false;
			}
			MtMember busMember = members.get(0);
			
			//获取第二行标题，并检查名称是否匹配
			Row titlesRow = sheet.getRow(1);
			boolean flag = false;
			if(titlesRow != null){
				flag = this.checkDataTitle(titlesRow, GOODS_TITLES);
			}
			if(!flag){
				returnMap.put("return_message", "执行导入失败:数据列不匹配");
				return false;
			}
			total = sheet.getLastRowNum() - 1;
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				MtGoods mtGoods = new MtGoods();
				Row row = sheet.getRow(i);
				Cell cell_0 = row.getCell(0);
				try {
					//执行结果
					if(cell_0 == null){
						cell_0 = row.createCell(0);
						cell_0.setCellValue("未导入");
					}
					String resultStr = cell_0.getStringCellValue();
					if(StringUtils.isNotEmpty(resultStr) && resultStr.equals("成功")){
						continue;
					}
					
					//商品分类
					Cell cell_1 = row.getCell(1);
					String categoryName = null;
					if(cell_1 != null){
						categoryName = cell_1.getStringCellValue();
					}
					if(StringUtils.isBlank(categoryName) || categoryName.length()>50){
						cell_0.setCellValue("失败:[分类]名称非空或太长");
						continue ;
					}
					
					//商品名称
					Cell cell_2 = row.getCell(2);
					String goodsName = null;
					if(cell_2 != null){
						goodsName = cell_2.getStringCellValue();
					}
					if(StringUtils.isBlank(goodsName) || goodsName.length()>50){
						cell_0.setCellValue("失败:[商品名称]非空或太长");
						continue ;
					}
					
					//价格下限
					Cell cell_3 = row.getCell(3);
					Double minPrice = null;
					if(cell_3 != null){
						try {
							minPrice = cell_3.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[参考价格下限]格式错误");
							continue ;
						}
					}
					if(minPrice == null){
						cell_0.setCellValue("失败:[参考价格下限]非空");
						continue ;
					}
					
					//价格上限
					Cell cell_4 = row.getCell(4);
					Double maxPrice = null;
					if(cell_4 != null){
						try {
							maxPrice = cell_4.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[参考价格上限]格式错误");
							continue ;
						}
					}
					if(maxPrice == null){
						cell_0.setCellValue("失败:[参考价格上限]非空");
						continue ;
					}
					
					//生产日期 
					Cell cell_5 = row.getCell(5);
					Date createTime = null;
					if(cell_5 != null){
						try {
							createTime = cell_5.getDateCellValue();
						} catch (Exception e) {
							cell_0.setCellValue("失败:[生产日期]格式错误");
							continue ;
						}
					}
					
					//生产地址
					Cell cell_6 = row.getCell(6);
					String createAddress = null;
					if(cell_6 != null){
						createAddress = cell_6.getStringCellValue();
					}
					if(StringUtils.isNotBlank(createAddress) && createAddress.length()>50){
						cell_0.setCellValue("失败:[生产地址]名称太长");
						continue ;
					}
					
					//厂家名称
					Cell cell_7 = row.getCell(7);
					String factory = null;
					if(cell_7 != null){
						factory = cell_7.getStringCellValue();
					}
					if(StringUtils.isNotBlank(factory) && factory.length()>50){
						cell_0.setCellValue("失败:[生产厂家]名称太长");
						continue ;
					}
					
					//品牌名称
					Cell cell_8 = row.getCell(8);
					String brand = null;
					if(cell_8 != null){
						brand = cell_8.getStringCellValue();
					}
					if(StringUtils.isNotBlank(brand) && brand.length()>50){
						cell_0.setCellValue("失败:[品牌]名称太长");
						continue ;
					}
					
					//库存 
					Cell cell_9 = row.getCell(9);
					Integer stockNum = null;
					if(cell_9 != null){
						try {
							stockNum = (int)cell_9.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[库存]格式错误");
							continue ;
						}
					}
					
					//保质期
					Cell cell_10 = row.getCell(10);
					Integer productTime = null;
					if(cell_10 != null){
						try {
							productTime = (int)cell_10.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[保质期]格式错误");
							continue ;
						}
					}
					
					//商品备注
					Cell cell_11 = row.getCell(11);
					String described = null;
					if(cell_11 != null){
						described = cell_11.getStringCellValue();
					}
					if(StringUtils.isNotBlank(described) && described.length()>100){
						cell_0.setCellValue("失败:[备注]名称太长");
						continue ;
					}
					
					//查询商品分类 
					List<MtCategory> categorys = categoryMapper.selectByName(categoryName);
					if(categorys == null || categorys.size() != 1){
						cell_0.setCellValue("失败:[分类]名称不存在");
						continue ;
					}
					MtCategory category = categorys.get(0);
					
					//校验商品名称是否重复
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("mmbId", busMember.getId());
					paramMap.put("categoryId", category.getId());
					int countByName = goodsMapper.countByName(goodsName, busMember.getId(), category.getId());
					if(countByName>0){
						cell_0.setCellValue("失败:[商品名称]已存在");
						continue ;
					}
					
					//封装数据到对象
					mtGoods.setGoodsId(UUIDCreater.getUUID());
					mtGoods.setStatus(DictionaryUtil.GOOD_STATUS_USING);
					mtGoods.setMmbId(busMember.getId());
					mtGoods.setName(goodsName);
					mtGoods.setCategoryId(category.getId());
					mtGoods.setCategoryName(categoryName);
					mtGoods.setCreateTime(createTime);
					mtGoods.setCreateAddress(createAddress);
					mtGoods.setFactory(factory);
					mtGoods.setBrand(brand);
					mtGoods.setStockNum(stockNum.longValue());
					mtGoods.setProductTime(productTime.toString());
					mtGoods.setMinPrice(minPrice);
					mtGoods.setMaxPrice(maxPrice);
					mtGoods.setDescribed(described);
					
					//执行插入sql
					int result = goodsMapper.insertSelective(mtGoods);
					if(result > 0){
						success++;
						cell_0.setCellValue("成功");
					}else {
						cell_0.setCellValue("失败");
					}
				} catch (Exception e) {
					cell_0.setCellValue("失败");
					e.printStackTrace();
				}
			}
			return true;                   
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			returnMap.put("总记录数", total+"条");
			returnMap.put("导入成功", success+"条");
			returnMap.put("导入失败", total-success+"条");
		}
	}
	
	*//**
	 * 订单导入
	 *//*
	@Override
	public boolean importOrders(Workbook work, Map<String, Object> returnMap) {
		int total = 0;
		int success = 0;
		try {
			//获取excel中第一个表
			Sheet sheet = work.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			if(lastRowNum>1001){
				returnMap.put("return_message", "执行导入失败:数据不能1000行!");
				return false;
			}
			
			//获取供应商名称
			Row firstRow = sheet.getRow(0);
			Cell buyerTitleCell = null;
			if(firstRow != null){
				buyerTitleCell = firstRow.getCell(0);
			}
			String buyTitleName = null;
			if(buyerTitleCell != null){
				buyTitleName = buyerTitleCell.getStringCellValue();
			}
			if(!"买方".equals(buyTitleName)){
				returnMap.put("return_message", "执行导入失败:数据列不匹配!");
				return false;
			}
			
			Cell buyerCell = firstRow.getCell(1);
			String buyerName = null;
			if(buyerCell != null){
				buyerName = buyerCell.getStringCellValue();
			}
			if(StringUtils.isBlank(buyerName)){
				returnMap.put("return_message", "执行导入失败:买家名称不可以为空!");
				return false;
			}
			
			Cell sellerTitleCell = firstRow.getCell(2);
			String sellTitleName = null;
			if(sellerTitleCell != null){
				sellTitleName = sellerTitleCell.getStringCellValue();
			}
			if(!"卖方".equals(sellTitleName)){
				returnMap.put("return_message", "执行导入失败:数据列不匹配!");
				return false;
			}
			
			Cell sellerCell = firstRow.getCell(3);
			String sellerName = null;
			if(sellerCell != null){
				sellerName = sellerCell.getStringCellValue();
			}
			if(StringUtils.isBlank(sellerName)){
				returnMap.put("return_message", "执行导入失败:卖家名称不可以为空!");
				return false;
			}
			
			//查询买方会员
			List<MtMember> buyMembers = mmbMapper.selectByName(buyerName);
			if(buyMembers==null || buyMembers.size() != 1){
				returnMap.put("return_message", "执行导入失败:无法匹配到买方");
				return false;
			}
			MtMember buyerMember = buyMembers.get(0);
			
			//查询卖方会员
			List<MtMember> sellMembers = mmbMapper.selectByName(sellerName);
			if(sellMembers==null || sellMembers.size() != 1){
				returnMap.put("return_message", "执行导入失败:无法匹配到卖方");
				return false;
			}
			MtMember sellerMember = sellMembers.get(0);
			
			
			//获取第二行标题，并检查名称是否匹配
			Row titlesRow = sheet.getRow(1);
			boolean flag = false;
			if(titlesRow != null){
				flag = this.checkDataTitle(titlesRow, ORDER_TITLES);
			}
			if(!flag){
				returnMap.put("return_message", "执行导入失败:数据列不匹配");
				return false;
			}
			
			total = sheet.getLastRowNum() - 1;
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell cell_0 = row.getCell(0);
				try {
					if(cell_0 == null){
						cell_0 = row.createCell(0);
						cell_0.setCellValue("未导入");
					}
					String resultStr = cell_0.getStringCellValue();
					if(StringUtils.isNotEmpty(resultStr) && resultStr.equals("成功")){
						continue;
					}
					
					//流水号
					Cell cell_1 = row.getCell(1);
					String code = null;
					if(cell_1 != null){
						try {
							code = (long)cell_1.getNumericCellValue()+"";
						} catch (Exception e) {
							cell_0.setCellValue("失败:[流水号]格式错误");
							continue ;
						}
					}
					if(StringUtils.isBlank(code) || code.length()>50){
						cell_0.setCellValue("失败:[流水号]非空或太长");
						continue ;
					}
					
					//商品名称
					Cell cell_2 = row.getCell(2);
					String goodsName = null;
					if(cell_2 != null){
						goodsName = cell_2.getStringCellValue();
					}
					if(StringUtils.isBlank(goodsName) || goodsName.length()>50){
						cell_0.setCellValue("失败:[商品名称]非空或太长");
						continue ;
					}
					
					//商品数量
					Cell cell_3 = row.getCell(3);
					Integer goodsNum = null;
					if(cell_3 != null){
						try {
							goodsNum = (int)cell_3.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[商品数量]格式错误");
							continue ;
						}
					}
					if(goodsNum == null){
						cell_0.setCellValue("失败:[商品数量]非空");
						continue ;
					}
					
					//商品单价
					Cell cell_4 = row.getCell(4);
					Double price = null;
					if(cell_4 != null){
						try {
							price = cell_4.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[单价]格式错误");
							continue ;
						}
					}
					if(price == null){
						cell_0.setCellValue("失败:[单价]非空");
						continue ;
					}
					
					//订单金额
					Cell cell_5 = row.getCell(5);
					Double money = null;
					if(cell_5 != null){
						try {
							money = cell_5.getNumericCellValue();
						} catch (Exception e) {
							e.printStackTrace();
							cell_0.setCellValue("失败:[金额]格式错误");
							continue ;
						}
					}
					if(money == null){
						cell_0.setCellValue("失败:[金额]非空");
						continue ;
					}
					
					//订单时间
					Cell cell_6 = row.getCell(6);
					Date orderTime = null;
					if(cell_6 != null){
						try {
							orderTime = cell_6.getDateCellValue();
						} catch (Exception e) {
							cell_0.setCellValue("失败:[时间]格式错误");
							continue ;
						}
					}
					
					if(orderTime == null){
						cell_0.setCellValue("失败:[时间]非空");
						continue ;
					}
					
					
					//根据流水号判断订单是否已经导入
					int count = mtOrdertitleMapper.getByCode(code);
					if(count>0){
						cell_0.setCellValue("失败:已经被导入");
						continue ;
					}
					
					List<MtGoods> goodsList = goodsMapper.getByName(goodsName,sellerMember.getId());
					if(goodsList == null || goodsList.size() != 1){
						cell_0.setCellValue("失败:[商品名]数据异常");
						continue ;
					}
					
					MtGoods mtGoods = goodsList.get(0);
					String categoryId = mtGoods.getCategoryId();
					MtCategory category = categoryMapper.getById(categoryId);
					
					//封装数据到订单头
					MtOrdertitle mtOrdertitle = new MtOrdertitle();
					String orderTitleId = UUIDCreater.getUUID();
					Integer titleCode = Integer.valueOf(SoleIdUtil.getSoleIdSingletion().getIntSoleId(BillsType.ORDER.getValue()));
					mtOrdertitle.setId(orderTitleId);
					mtOrdertitle.setOrdertitleCode(titleCode);
					mtOrdertitle.setBuyersId(buyerMember.getId());
					mtOrdertitle.setBuyersName(buyerMember.getMmbSname());
					MtMmbWarehouse buyerAddress = null;
					try {
						List<MtMmbWarehouse> buyWareHouses = mtMmbWarehouseMapper.getMmbWareHouseByMmbId(buyerMember.getId());
						if(buyWareHouses != null && buyWareHouses.size()>0){
							buyerAddress = buyWareHouses.get(0);
							mtOrdertitle.setBuyersAddressId(buyerAddress.getId());
							mtOrdertitle.setBuyersAddressName(buyerAddress.getAddress());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					mtOrdertitle.setSellersId(sellerMember.getId());
					mtOrdertitle.setSellersName(sellerMember.getMmbSname());
					MtMmbWarehouse sellerAddress = null;
					try {
						List<MtMmbWarehouse> sellWareHouses = mtMmbWarehouseMapper.getMmbWareHouseByMmbId(sellerMember.getId());
						if(sellWareHouses != null && sellWareHouses.size()>0){
							sellerAddress = sellWareHouses.get(0);
							mtOrdertitle.setSellersAddressId(sellerAddress.getId());
							mtOrdertitle.setSellersAddressName(sellerAddress.getAddress());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					mtOrdertitle.setCreateTime(orderTime);
					mtOrdertitle.setTotalMoney(money);
					mtOrdertitle.setExecuteStartTime(orderTime);
					mtOrdertitle.setExecuteEndTime(orderTime);
					//锁定
					mtOrdertitle.setStatus(4);
					//未中止
					mtOrdertitle.setStopStatus(1);
					mtOrdertitle.setRemark(code);
					//货款两清
					mtOrdertitle.setWorkflowTypeId(1);
					mtOrdertitle.setPayTime(orderTime);
					MtMmbBankAccount buyerBank = null;
					try {
						List<MtMmbBankAccount> buyerBanks = mtMmbBankAccountMapper.getMmbBankAccountByMmbId(buyerMember.getId());
						if(buyerBanks != null && buyerBanks.size()>0){
							buyerBank = buyerBanks.get(0);
							mtOrdertitle.setPayBank(buyerBank.getAccountno());
							mtOrdertitle.setPayAccount(buyerBank.getBankname());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//人工终止状态为未中止
					mtOrdertitle.setExecuteStatus(1);
					MtMmbBankAccount sellerBank = null;
					try {
						List<MtMmbBankAccount> sellerBanks = mtMmbBankAccountMapper.getMmbBankAccountByMmbId(sellerMember.getId());
						if(sellerBanks != null && sellerBanks.size()>0){
							sellerBank = sellerBanks.get(0);
							mtOrdertitle.setGetBank(sellerBank.getAccountno());
							mtOrdertitle.setGetAccount(sellerBank.getBankname());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					mtOrdertitle.setLockTime(orderTime);
					mtOrdertitle.setFinishTime(orderTime);
					//mtOrdertitle.setUserId(userId);
					//mtOrdertitle.setUserName(userName);
					mtOrdertitle.setOperateTime(orderTime);
					
					
					//封装数据到订单项
					MtOrder mtOrder = new MtOrder();
					mtOrder.setId(UUIDCreater.getUUID());
					mtOrder.setOredertitleCode(mtOrdertitle.getId());
					mtOrder.setGoodsId(mtGoods.getGoodsId());
					mtOrder.setGoodsName(goodsName);
					mtOrder.setCategoryId(categoryId);
					mtOrder.setCategoryName(category != null?category.getName():"");
					mtOrder.setGoodsNum(goodsNum.doubleValue());
					mtOrder.setPrice(price);
					mtOrder.setMoney(money);
					mtOrder.setQuoteId(null);
					mtOrder.setBuyersId(buyerMember.getId());
					mtOrder.setBuyersName(buyerMember.getMmbSname());
					mtOrder.setSellersId(sellerMember.getId());
					mtOrder.setSellersName(sellerMember.getMmbSname());
					mtOrder.setStatus(4);
					mtOrder.setRemark(code);
					mtOrder.setWorkflowType(1);
					mtOrder.setStopStatus(1);
					mtOrder.setExecuteStatus(1);
					mtOrder.setLockTime(orderTime);
					mtOrder.setFinishTime(orderTime);
					//mtOrder.setUserId(userId);
					//mtOrder.setUserName(userName);
					mtOrder.setCreateTime(orderTime);
					mtOrder.setOrdertitleNumber(mtOrdertitle.getOrdertitleCode());
					
					//同一个事务中保存
					orderService.createOrder(mtOrdertitle, mtOrder);
					success++;
					cell_0.setCellValue("成功");
				} catch (Exception e) {
					cell_0.setCellValue("失败");
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			returnMap.put("总记录数", total+"条");
			returnMap.put("导入成功", success+"条");
			returnMap.put("导入失败", total-success+"条");
		}
	}*/

	public boolean importGoods(Workbook work, Map<String, Object> returnMap) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean importOrders(Workbook work, Map<String, Object> returnMap) {
		// TODO Auto-generated method stub
		return false;
	}
}