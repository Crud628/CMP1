package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Shopkeepers;
import com.common.DataUtil;

/**
 * Sid  商家编号
 * Sname  商家名称
 * Suser  商家账号
 * Spwd  登录密码
 * Stel  商家电话
 * @author 方凯
 */
public class ShopkeepersService {
	/**
	 * 增加
	 * insert into shopkeepers values(sseq.nextval,
	       '煎饼果子','16301253',md5('123456'),'12345678912');
	 * @param shop  店铺对象
	 * @return 1成功  0失败
	 */
	public int ShopInsert(Shopkeepers shop) {
		String sql="insert into shopkeepers values(sseq.nextval,?,?,md5(?),?)";
		Object data[]= {shop.getSname(),shop.getSuser(),shop.getSpwd(),shop.getStel()};
		if (DataUtil.update(sql, data)) {
			return 1;
		}
		return 0;
	}
	
	
	/**
	 * 删除
	 * delete from shopkeepers where sid=4
	 * @param id 根据sid删除对应记录
	 * @return
	 */
	public int ShopDeleteById(int id) {
		String sql = "delete from shopkeepers where sid=?";
		Object data[] = {id};
		if (DataUtil.update(sql, data)) {
			return 1;
		}
		return 0;
	}
	/**
	 * 按照id修改
	 * update shopkeepers set sname='测试修改',suser='111',spwd='1111',stel='1111' where sid=4
	 * @param shop
	 * @return
	 */
	public int shopUpdate(Shopkeepers shop) {
		String sql = "update shopkeepers set sname=?,suser=?,spwd=?,stel=? where sid=?";
		Object data[] = {shop.getSname(),shop.getSuser(),shop.getSpwd(),shop.getStel(),shop.getSid()};
		if (DataUtil.update(sql, data)) {
			return 1;
		}
		return 0;
	}
	/**
	 * 按照id查询单条数据
	 * select * from shopkeepers where sid=?
	 * @param id
	 * @return
	 */
	public Shopkeepers getShopById(int id) {
		String sql = "select * from shopkeepers where sid=?";
		Object data[] = {id};
		//调用common的工具类
		ResultSet rs = DataUtil.select(sql, data);
		if (rs!=null) {
			try {
				if (rs.next()) {
					//将结果集的数据存入到对象中
					Shopkeepers shop = new Shopkeepers();
					shop.setSid(rs.getString(1));
					shop.setSname(rs.getString(2));
					shop.setSuser(rs.getString(3));
					shop.setSpwd(rs.getString(4));
					shop.setStel(rs.getString(5));
					return shop;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					DataUtil.pst.close();
					DataUtil.con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * 查询得到全部数据
	 * @return
	 */
	public List<Shopkeepers> shopAll() {
		String sql = "select * from Shopkeepers";
		ResultSet rs = DataUtil.select(sql);
		List<Shopkeepers> data = new ArrayList<Shopkeepers>();
		if (rs!=null) {
			try {
				while(rs.next()) {
					Shopkeepers shop = new Shopkeepers();
					shop.setSid(rs.getString(1));
					shop.setSname(rs.getString(2));
					shop.setSuser(rs.getString(3));
					shop.setSpwd(rs.getString(4));
					shop.setStel(rs.getString(5));
					data.add(shop);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					DataUtil.pst.close();
					DataUtil.con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	
	public static void main(String[] args) {
		/*增加测试
		 * Shopkeepers shop = new Shopkeepers(); shop.setSname("测试");
		 * shop.setSuser("1111"); shop.setSpwd("1111"); shop.setStel("111");
		 * System.out.println(new ShopkeepersService().ShopInsert(shop));
		 */
		
		
		
	}
}
