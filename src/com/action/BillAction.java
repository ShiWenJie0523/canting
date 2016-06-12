package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.Bill;
import com.bean.BillGoods;
import com.bean.CanDan;
import com.bean.Custom;
import com.bean.DiningTable;
import com.bean.Goods;
import com.bean.GoodsCate;
import com.dao.CommonDao;
import com.util.ActionUtil;
import com.util.ClassBeanFactory;
import com.util.Utils;

/**
 * 桌位管理
 * 预定和收款
 * 
 *
 */
public class BillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private CommonDao commDao = (CommonDao)ClassBeanFactory.getBean("commDao");
	
	
	public String diningtableList(){
		List<Object> seatCountList = commDao.getSqlList("select seatCount from t_DiningTable group by seatCount");
		setAttribute("seatCountList", seatCountList);
		StringBuffer where = new StringBuffer();
		where.append(" where 1=1");
		Object[] objArr = new Object[0];
		
		String status = getParameter("status");
		if(!status.equals("")){
			where.append(" and status=?");
			objArr = addNames(objArr, Integer.parseInt(status));
		}
		String seatCount = getParameter("seatCount");
		if(!seatCount.equals("")){
			where.append(" and seatCount=?");
			objArr = addNames(objArr, Integer.parseInt(seatCount));
		}
		String tableNum = getParameter("tableNum");
		if(!tableNum.equals("")){
			where.append(" and tableNum=?");
			objArr = addNames(objArr, tableNum);
		}
		String mobile = getParameter("mobile");
		if(!mobile.equals("")){
			where.append(" and mobile=?");
			objArr = addNames(objArr, mobile);
		}
		long count = commDao.getObjectCount(DiningTable.class,where.toString(),objArr);
		List<DiningTable> list = commDao.getObjectList(DiningTable.class,where.toString(),objArr, getPageNum(), getNumPerPage());
		for(DiningTable dt : list){
			if(dt.getStatus()==2){
				if(dt.getBookTime().getTime()<new Date().getTime()){
					dt.setStatus(3);
				}
			}
		}
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addDiningTable() throws IOException{
		PrintWriter out = getWriter();
		DiningTable bean = new DiningTable();
		String tableNum = getParameter("tableNum");
		long c = commDao.getObjectCount(DiningTable.class, " where tableNum=?", tableNum);
		if(c>0){
			out.write(Utils.outPutMsg("300", "该桌位编号已存在", "", "", false, ""));
			out.flush();
			return null;
		}
		bean.setTableNum(tableNum);
		bean.setSeatCount(Integer.parseInt(getParameter("seatCount")));
		bean.setStatus(0);
		commDao.addObject(bean);
		out.write(Utils.outPutMsg("200", "添加成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
		out.flush();
		return null;
	}
	
	public String deleDiningTable() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		commDao.deleObject(DiningTable.class, id);
		out.write(Utils.outPutMsg("200", "删除成功", "", "", false, ""));
		out.flush();
		return null;
	}
	
	public String updateDiningTable() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);
		String tableNum = getParameter("tableNum");
		if(bean.getTableNum().equals(tableNum)){
			bean.setSeatCount(Integer.parseInt(getParameter("seatCount")));
			commDao.updateObject(bean);
			out.write(Utils.outPutMsg("200", "修改成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
			out.flush();
			return null;
			
		}
		
		
		
		long c = commDao.getObjectCount(DiningTable.class, " where tableNum=?", tableNum);
		if(c>0){
			out.write(Utils.outPutMsg("300", "该桌位编号已存在", "", "", false, ""));
			out.flush();
			return null;
		}
		bean.setTableNum(tableNum);
		bean.setSeatCount(Integer.parseInt(getParameter("seatCount")));
		commDao.updateObject(bean);
		out.write(Utils.outPutMsg("200", "修改成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
		out.flush();
		return null;
	}
	
	public String findDiningTable(){
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);
		setAttribute("bean", bean);
		return ActionUtil.Result.SUCCESS;
	}
	
	
	/**
	 * 预定
	 * @return
	 * @throws IOException 
	 */
	public String yuding() throws IOException{
		PrintWriter out = getWriter();
		Date bookTime = Utils.str2Date(getParameter("bookTime"), "yyyy-MM-dd HH:mm");
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);
		if(bean.getStatus()!=0){
			out.write(Utils.outPutMsg("300", "该座位已被占用", "", "", false, ""));
			out.flush();
			return null;
		}
		bean.setYudingfei(Double.parseDouble(getParameter("yudingfei")));
		bean.setZhifu(getParameter("zhifu"));
		bean.setMobile(getParameter("mobile"));
		bean.setName(getParameter("name"));
		bean.setBookTime(bookTime);
		bean.setStatus(2);
		commDao.updateObject(bean);
		out.write(Utils.outPutMsg("200", "预定成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
		out.flush();
		return null;
	}
	/**
	 * 重置桌位状态
	 * @return
	 * @throws IOException 
	 */
	public String resetDiningTable() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);
		if(bean.getStatus()<2){
			out.write(Utils.outPutMsg("300", "该桌位不能操作取消预定", "", "", false, ""));
			out.flush();
			return null;
		}
		bean.setMobile("");
		bean.setName("");
		bean.setBookTime(null);
		bean.setStatus(0);
		bean.setZhifu("");
		bean.setYudingfei(0);
		
		commDao.updateObject(bean);
		out.write(Utils.outPutMsg("200", "重置成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
		out.flush();
		return null;
	}
	
	/**
	 * 开台前确认
	 * @return
	 */
	public String openForm(){
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);
		setAttribute("bean", bean);
		return ActionUtil.Result.SUCCESS;
	}
	/**
	 * 有人桌位状态
	 * @return
	 * @throws IOException 
	 */
	public String useDiningTable() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);

		if(bean.getStatus()==3 ){
			out.write(Utils.outPutMsg("300", "该桌位预定超时，开台失败，！请取消预定！", "", "", false, ""));
			out.flush();
			return null;
		}
		if(bean.getStatus()!=2 && bean.getStatus()!=0){
			out.write(Utils.outPutMsg("300", "该桌位不能操作为有人状态", "", "", false, ""));
			out.flush();
			return null;
		}
		if(bean.getStatus()==0){
			bean.setMobile(getParameter("mobile"));
			bean.setName(getParameter("name"));
		}
		bean.setStatus(1);
		commDao.updateObject(bean);
		/**查找会员**/
		List<Custom> customList = commDao.getList(Custom.class, " where mobile=?", bean.getMobile());
		Custom custom = null;
		if(customList.size()>0){
			custom = customList.get(0);
			custom.setFreshTime(new Date());
		}
		/**生成消费账单**/
		Bill bill = new Bill();
		bill.setYudingfei(bean.getYudingfei());
		bill.setZhifu(bean.getZhifu());
		bill.setSearilNum(Utils.date2Str(new Date(), "yyyyMMddHHmmss"));
		bill.setTableNum(bean.getTableNum());
		bill.setTotal_price(0);
		bill.setZhekou_price(0);
		bill.setReal_price(0);
		bill.setMobile(bean.getMobile());
		if(custom!=null){
			if(custom.getClevel()<3){
				bill.setGuazhang(0);
			}else{
				bill.setGuazhang(1);
				bill.setEffect(1);
			}
		}else{
			bill.setGuazhang(0);
		}
		commDao.addObject(bill);
		out.write(Utils.outPutMsg("200", "设置成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
		out.flush();
		return null;
	}
	
	public String useDiningTable2() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		DiningTable bean = commDao.findObjectById(DiningTable.class, id);
		bean.setMobile(getParameter("mobile"));
		bean.setName(getParameter("name"));
		bean.setStatus(1);
		commDao.updateObject(bean);
		/**查找会员**/
		List<Custom> customList = commDao.getList(Custom.class, " where mobile=?", bean.getMobile());
		Custom custom = null;
		if(customList.size()>0){
			custom = customList.get(0);
			custom.setFreshTime(new Date());
		}
		/**生成消费账单**/
		Bill bill = new Bill();
		bill.setSearilNum(Utils.date2Str(new Date(), "yyyyMMddHHmmss"));
		bill.setTableNum(bean.getTableNum());
		bill.setTotal_price(0);
		bill.setZhekou_price(0);
		bill.setReal_price(0);
		bill.setMobile(bean.getMobile());
		if(custom!=null){
			if(custom.getClevel()<3){
				bill.setGuazhang(0);
			}else{
				bill.setGuazhang(1);
				bill.setEffect(1);
			}
		}else{
			bill.setGuazhang(0);
		}
		commDao.addObject(bill);
		out.write(Utils.outPutMsg("200", "设置成功", "diningtableList", "", false, "act_Bill_diningtableList.action"));
		out.flush();
		return null;
	}
	
	/**
	 * 消费账单列表
	 * @return
	 */
	public String billList(){
		StringBuffer where = new StringBuffer();
		where.append(" where finish=?");
		Object[] objArr = createNames(0);
		long count = commDao.getObjectCount(Bill.class,where.toString(),objArr);
		List<Bill> list = commDao.getObjectList(Bill.class,where.toString(),objArr,getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	/**
	 * 历时账单
	 * @return
	 */
	public String historyBill(){
		long count = commDao.getObjectCount(Bill.class);
		List<Bill> list = commDao.getObjectList(Bill.class,getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	/**
	 * 结束消费
	 * @return
	 * @throws IOException 
	 */
	public String finish() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		Bill bean = commDao.findObjectById(Bill.class, id);
		if(bean.getStatus()==0){
			out.write(Utils.outPutMsg("300", "请先结账", "", "", false, ""));
			out.flush();
			return null;
		}
		bean.setFinish(1);
		List<Custom> customList = commDao.getList(Custom.class, " where mobile=?", bean.getMobile());
		Custom custom = null;
		if(customList.size()>0){
			custom = customList.get(0);
			custom.setJifen(custom.getJifen()+(int)bean.getZhekou_price());
			if(custom.getJifen()>=500){
				custom.setClevel(1);
			}
			if(custom.getJifen()>=1500){
				custom.setClevel(2);
			}
			if(custom.getJifen()>=3000){
				custom.setClevel(3);
			}
			commDao.updateObject(custom);
		}
		commDao.updateObject(bean);
		//修改桌位状态
		DiningTable dt = commDao.getList(DiningTable.class, " where tableNum=?", bean.getTableNum()).get(0);
		dt.setMobile("");
		dt.setName("");
		dt.setBookTime(null);
		dt.setStatus(0);
		dt.setZhifu("");
		dt.setYudingfei(0);
		commDao.updateObject(dt);
		out.write(Utils.outPutMsg("200", "操作成功", "billList", "", false, "act_Bill_billList.action"));
		out.flush();
		return null;
	}
	
	
	public String payForm(){
		int id = Integer.parseInt(getParameter("id"));
		Bill bean = commDao.findObjectById(Bill.class, id);
		setAttribute("bean", bean);
		return ActionUtil.Result.SUCCESS;
	}
	
	/**
	 * 结账
	 * @return
	 * @throws IOException 
	 */
	public String pay() throws IOException{
		int id = Integer.parseInt(getParameter("id"));
		Bill bean = commDao.findObjectById(Bill.class, id);
		double real_price = Double.parseDouble(getParameter("real_price"));
		if(real_price>0){
			bean.setZhaoli_price(real_price-bean.getZhekou_price());
		}
		bean.setReal_price(real_price);
		bean.setStatus(1);
		bean.setEffect(1);
		commDao.updateObject(bean);
		PrintWriter out = getWriter();
		out.write(Utils.outPutMsg("200", "操作成功", "billList", "", false, "act_Bill_billList.action"));
		out.flush();
		return null;
	}
	
	/**
	 * 菜单
	 * @return
	 */
	public String caidan(){
		int id = Integer.parseInt(getParameter("id"));
		Bill bill = commDao.findObjectById(Bill.class, id);
		setAttribute("bill", bill);
		/**单点**/
		ArrayList<CanDan> list = new ArrayList<CanDan>();
		List<GoodsCate> cateList = commDao.getList(GoodsCate.class);
		for(GoodsCate cate : cateList){
			CanDan candan = new CanDan();
			candan.setCateName(cate.getCateName());
			candan.setGoodsList(commDao.getList(Goods.class, " where goodsCateid=? and ground=?", cate.getId(),1));
			list.add(candan);
		}
		setAttribute("candanList", list);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addCaiDan() throws IOException{
		PrintWriter out = getWriter();
		String[] goods_ids = getReq().getParameterValues("goods_ids");
		String[] taocan_ids = getReq().getParameterValues("taocan_ids");
		if(goods_ids==null && taocan_ids==null){
			out.write(Utils.outPutMsg("300", "没有菜被选择", "", "", true, ""));
			out.flush();
			return null;
		}
		int billId = Integer.parseInt(getParameter("billId"));
		Bill bill = commDao.findObjectById(Bill.class, billId);
		//单点菜品
		if(goods_ids!=null){
			for(int i=0;i<goods_ids.length;i++){
				int id = Integer.parseInt(goods_ids[i]);
				Goods goods = commDao.findObjectById(Goods.class, id);
				int billNum = Integer.parseInt(getParameter("goods_billNum"+id));
				BillGoods bg = new BillGoods();
				bg.setBill(bill);
				bg.setType(0);
				bg.setName(goods.getName());
				bg.setPrice(goods.getPrice()*billNum);
				bg.setBillNum(billNum);
				commDao.addObject(bg);
			}
		}
		
		//修改订单
		List<BillGoods> list = commDao.getList(BillGoods.class, " where bill.id=?", bill.getId());
		double d = 0;
		for(int i=0;i<list.size();i++){
			BillGoods bg = list.get(i);
			d = d+bg.getPrice();
		}
		bill.setTotal_price(d);
		List<Custom> customList = commDao.getList(Custom.class, " where mobile=?", bill.getMobile());
		Custom custom = null;
		if(customList.size()>0){
			custom = customList.get(0);
		}
		if(custom!=null){
			int clevel = custom.getClevel();
			double rate = 1;
			switch (clevel) {
				case 0:rate=1;break;
				case 1:rate=0.9;break;
				case 2:rate=0.8;break;
				case 3:rate=0.7;break;
			}
			bill.setZhekou_price(bill.getTotal_price()*rate);
		}else{
			bill.setZhekou_price(bill.getTotal_price());
		}
		commDao.updateObject(bill);
		out.write(Utils.outPutMsg("200", "点菜成功", "billList", "", true, "act_Bill_billList.action"));
		out.flush();
		return null;
	}
	
	/**
	 * 已点菜单
	 * @return
	 */
	public String caidanList(){
		int id = Integer.parseInt(getParameter("id"));
		Bill bill = commDao.findObjectById(Bill.class, id);
		List<BillGoods> list = commDao.getList(BillGoods.class, " where billid=?", bill.getId());
		setAttribute("list", list);
		return ActionUtil.Result.SUCCESS;
	}
	
	/**
	 * 厨房传单
	 * @return
	 */
	public String chufangList(){
		StringBuffer where = new StringBuffer();
		where.append(" where bill.effect=? and shang=?");
		Object[] objArr = createNames(1,0);
		
		long count = commDao.getObjectCount(BillGoods.class,where.toString(),objArr);
		List<BillGoods> list = commDao.getObjectList(BillGoods.class,where.toString(),objArr, getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	/**
	 * 完成上菜
	 * @return
	 * @throws IOException 
	 */
	public String shangcai() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		BillGoods bean = commDao.findObjectById(BillGoods.class, id);
		bean.setShang(1);
		out.write(Utils.outPutMsg("200", "上菜成功", "chufangList", "", false, "act_Bill_chufangList.action"));
		out.flush();
		return null;
	}
}
