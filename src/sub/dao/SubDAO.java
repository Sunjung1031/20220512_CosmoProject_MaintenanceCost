package sub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jdbc.JdbcUtil;
import main.model.Main;
import sub.model.Sub;
import sub.model.SubPaging;
import unit.model.Unit;

public class SubDAO {
	private Sub sub = new Sub();
	private Sub subA = new Sub();
	private Unit unit = new Unit();
	private Main main = new Main();
	private Main mainA = new Main();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<Sub> subMainSelectAll(Connection conn, Map pagingValues)
			throws SQLException {
		ArrayList<Sub> SubMixed = new ArrayList<>();
		SubPaging subPaging = (SubPaging) pagingValues.get("paging");
		if (pagingValues.get("search") != null) {
			String search = (String) pagingValues.get("search");
			pstmt = conn.prepareStatement(
					"select * from ( select w.no 'w.no' , w.water_ind 'w.water_ind', e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind', u.name 'u.name', u.hire 'u.hire' , u.lease 'u.lease' , u.rent_fee 'u.rent_fee' , u.period 'u.period', u.size 'u.size', u.coming 'u.coming', u.leaving 'u.leaving' , m.memberId 'm.memberId', m.name 'm.name', m.total_size 'm.total_size', m.com_heat 'm.com_heat', m.com_clean 'm.com_clean', m.com_ev 'm.com_ev', m.com_es 'm.com_es', m.com_maintain 'm.com_maintain', m.com_insur 'm.com_insur', m.com_labor 'm.com_labor', m.com_water 'm.com_water', m.com_ele 'm.com_ele' from ele e, water w, unit u , main m where w.no = e.no and e.no =u.no group by e.no "
							+ "	 having w.no like ? or w.water_ind like ? or  e.ele_basic  like ? or e.ele_ind  like ? or u.name like ? or u.hire like ? or u.lease like ? or  m.com_heat like ? or  m.com_clean  like ? or  m.com_ev  like ? or  m.com_es  like ? or  m.com_maintain  like ? or  m.com_insur like ? or m.com_labor like ? or m.com_water like ? or m.com_ele like ?\r\n"
							+ "	  ) as t limit ? offset ?");
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setString(4, "%" + search + "%");
			pstmt.setString(5, "%" + search + "%");
			pstmt.setString(6, "%" + search + "%");
			pstmt.setString(7, "%" + search + "%");
			pstmt.setString(8, "%" + search + "%");
			pstmt.setString(9, "%" + search + "%");
			pstmt.setString(10, "%" + search + "%");
			pstmt.setString(11, "%" + search + "%");
			pstmt.setString(12, "%" + search + "%");
			pstmt.setString(13, "%" + search + "%");
			pstmt.setString(14, "%" + search + "%");
			pstmt.setString(15, "%" + search + "%");
			pstmt.setString(16, "%" + search + "%");
			pstmt.setInt(17, subPaging.getLimit());
			pstmt.setInt(18,
					subPaging.getLimit() * (subPaging.getCurrentPage() - 1));
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							rs.getInt(4), rs.getString(5), rs.getString(6),
							rs.getString(7), rs.getInt(8), rs.getString(9),
							rs.getInt(10), rs.getDate(11), rs.getDate(12),
							rs.getString(13), rs.getString(14), rs.getInt(15),
							rs.getInt(16), rs.getInt(17), rs.getInt(18),
							rs.getInt(19), rs.getInt(20), rs.getInt(21),
							rs.getInt(22), rs.getInt(23), rs.getInt(24));
					SubMixed.add(sub);
				}
			} catch (Exception e) {
				e.getMessage();
				System.out.println(" error :  subMainSelectAll");
			}
		} else {
			pstmt = conn.prepareStatement(
					"select * from ( select w.no 'w.no' , w.water_ind 'w.water_ind',e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind' , u.size 'u.size', u.coming 'u.coming', m.total_size 'm.total_size', m.com_heat 'm.com_heat', m.com_clean 'm.com_clean', m.com_ev 'm.com_ev', m.com_es 'm.com_es',m.com_maintain 'm.com_maintain', m.com_insur 'm.com_insur', m.com_labor 'm.com_labor', m.com_water 'm.com_water', m.com_ele 'm.com_ele' , u.name 'u.name' from ele e, water w, unit u , main m where w.no = e.no and e.no =u.no group by e.no ) as t limit ? offset ? ");
			pstmt.setInt(1, subPaging.getLimit());
			pstmt.setInt(2,
					subPaging.getLimit() * (subPaging.getCurrentPage() - 1));
			try {
				rs = pstmt.executeQuery();
				// main , com_water, total_size, com_ele, com_heat,
				// com_maintain, com_es, com_ev, com_insur, com
				// sub , no ,water_ind, ele_basic, ele_ind
				// unit , size, coming
				// Main(Sub sub, Main main, Unit unit);
				while (rs.next()) {
					subA = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							rs.getInt(4));
					unit = new Unit(rs.getInt(5), rs.getDate(6), rs.getString(17));
					mainA = new Main(rs.getInt(7), rs.getInt(8), rs.getInt(9),
							rs.getInt(10), rs.getInt(11), rs.getInt(12),
							rs.getInt(13), rs.getInt(14), rs.getInt(15),rs.getInt(16));
					sub = new Sub(subA, mainA , unit);
					SubMixed.add(sub);
				}
				// while (rs.next()) {
				// sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
				// rs.getInt(4), rs.getString(5), rs.getString(6),
				// rs.getString(7), rs.getInt(8) , rs.getString(9),
				// rs.getInt(10), rs.getDate(11), rs.getDate(12),
				// rs.getString(13), rs.getString(14), rs.getInt(15),
				// rs.getInt(16), rs.getInt(17), rs.getInt(18),
				// rs.getInt(19), rs.getInt(20), rs.getInt(21),
				// rs.getInt(22), rs.getInt(23), rs.getInt(24));
				// SubMixed.add(sub);
				// }
			} catch (Exception e) {
				System.out.println(" error :  subMainSelectAll");
				e.getMessage();
			}
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		return SubMixed;
	}

	public Sub eleSelectByNo(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select *from ele where no =?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4));
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
			System.out.println(" error :  eleSelectByNo");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return sub;
	}

	public Sub waterSelectByNo(Connection conn, int no) throws SQLException {
		try {
			pstmt = conn.prepareStatement("select *from water where no =?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2));
			}

		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  waterSelectByNo");
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return sub;
	}
	public void eleInsert(Connection conn, Sub sub) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into ele values(?, ?,?)")) {
			pstmt.setInt(1, sub.getNo());
			pstmt.setInt(2, sub.getEle_basic());
			pstmt.setInt(3, sub.getEle_ind());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  eleInsert");
		}
	}
	public void waterInsert(Connection conn, Sub sub) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into water values(?,?)")) {

			pstmt.setInt(1, sub.getNo());
			pstmt.setInt(2, sub.getWater_ind());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
			System.out.println(" error :  waterInsert");
		}
	}

	public void autoSubInsert(Connection conn, int num) throws SQLException {
		SubDAO subdao = new SubDAO();
		int i = 0;
		int j = 0;
		while (i <= num) {
			while (true) {
				if (subdao.checkIsSubNullByNo(conn, j)) {
					++i;
					try (PreparedStatement pstmt = conn.prepareStatement(
							"insert into water values(?,?)")) {
						pstmt.setInt(1, j);
						pstmt.setInt(2, 0);
						pstmt.executeUpdate();
					} catch (Exception e) {
					}
					try (PreparedStatement pstmt = conn.prepareStatement(
							"insert into ele values(?,?,?)")) {
						pstmt.setInt(1, j);
						pstmt.setInt(2, 0);
						pstmt.setInt(3, 0);
						pstmt.executeUpdate();
					} catch (Exception e) {
						System.out.println("error : autoSubInsert");
					}
					conn.commit();
					break;
				}
				j++;
			}
		}
		JdbcUtil.close(pstmt);
	}

	public void eleUpdate(Connection conn, Sub Sub) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update ele set ele_basic=?, ele_ind=? where no=?")) {
			pstmt.setInt(1, Sub.getEle_basic());
			pstmt.setInt(2, Sub.getEle_ind());
			pstmt.setInt(3, Sub.getNo());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  eleUpdate");
		}
	}
	public void waterUpdate(Connection conn, Sub Sub) throws SQLException {

		try (PreparedStatement pstmt = conn
				.prepareStatement("update water set water_ind=? where no=?")) {
			pstmt.setInt(1, Sub.getWater_ind());
			pstmt.setInt(2, Sub.getNo());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			System.out.println(" error :  waterUpdate");
			e.getMessage();
		} finally {

		}
	}

	public Sub getSubByNo(Connection conn, int no) throws SQLException {
		ArrayList<Sub> SubMixed = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(
					"select w.no, w.water_ind, e.ele_basic, e.ele_ind from ele e, water w where w.no = e.no having w.no = ? ");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4));
			}
		} catch (Exception e) {
			System.out.println(" error :  getSubByNo");
			e.getMessage();

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return sub;
	}

	public boolean checkIsSubNullByNo(Connection conn, int no) {
		boolean result = true;
		try {
			pstmt = conn.prepareStatement(
					"select w.no, w.water_ind, e.ele_basic, e.ele_ind from ele e, water w where w.no = e.no having w.no = ? ");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
		} catch (Exception e) {
			System.out.println(" error :  checkIsSubNullByNo");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return result;
	}

	public boolean deleteSubByNo(Connection conn, int no) {
		boolean waterResult = true;
		boolean eleResult = true;
		try {
			pstmt = conn.prepareStatement("delete from water where no = ? ");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			waterResult = false;
			System.out.println(" error :  deleteSubByNo_water");
			e.getMessage();
		}
		try {
			pstmt = conn.prepareStatement("delete from ele where no = ? ");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			eleResult = false;
			System.out.println(" error :  deleteSubByNo_ele");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		if (waterResult && eleResult)
			return true;
		else
			return false;
	}

	public int subMainAllCount(Connection conn, String search) {
		int result = 0;
		try {
			if (search != null) {
				pstmt = conn.prepareStatement(
						"select count(t.allc) from ( select w.no 'w.no' , w.water_ind 'w.water_ind', e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind', u.name allc, u.hire 'u.hire' , u.lease 'u.lease' , u.rent_fee 'u.rent_fee' , u.period 'u.period', u.size 'u.size', u.coming 'u.coming', u.leaving 'u.leaving' , m.memberId 'm.memberId', m.name 'm.name', m.total_size 'm.total_size', m.com_heat 'm.com_heat', m.com_clean 'm.com_clean', m.com_ev 'm.com_ev', m.com_es 'm.com_es', m.com_maintain 'm.com_maintain', m.com_insur 'm.com_insur', m.com_labor 'm.com_labor', m.com_water 'm.com_water', m.com_ele 'm.com_ele' from ele e, water w, unit u , main m where w.no = e.no and e.no =u.no group by e.no "
								+ "	 having w.no like ? or w.water_ind like ? or  e.ele_basic  like ? or e.ele_ind  like ? or u.name like ? or u.hire like ? or u.lease like ? or  m.com_heat like ? or  m.com_clean  like ? or  m.com_ev  like ? or  m.com_es  like ? or  m.com_maintain  like ? or  m.com_insur like ? or m.com_labor like ? or m.com_water like ? or m.com_ele like ? "
								+ " ) as t");
				pstmt.setString(1, "%" + search + "%");
				pstmt.setString(2, "%" + search + "%");
				pstmt.setString(3, "%" + search + "%");
				pstmt.setString(4, "%" + search + "%");
				pstmt.setString(5, "%" + search + "%");
				pstmt.setString(6, "%" + search + "%");
				pstmt.setString(7, "%" + search + "%");
				pstmt.setString(8, "%" + search + "%");
				pstmt.setString(9, "%" + search + "%");
				pstmt.setString(10, "%" + search + "%");
				pstmt.setString(11, "%" + search + "%");
				pstmt.setString(12, "%" + search + "%");
				pstmt.setString(13, "%" + search + "%");
				pstmt.setString(14, "%" + search + "%");
				pstmt.setString(15, "%" + search + "%");
				pstmt.setString(16, "%" + search + "%");
			} else {
				pstmt = conn.prepareStatement(
						"select count(t.allc) from ( select w.no 'w.no' , w.water_ind 'w.water_ind',e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind', u.name 'u.name',u.hire 'u.hire' , u.lease 'u.lease' , u.rent_fee 'u.rent_fee' , u.period 'u.period', u.size allc, u.coming 'u.coming', u.leaving 'u.leaving' , m.memberId 'm.memberId', m.name 'm.name', m.total_size 'm.total_size', m.com_heat 'm.com_heat', m.com_clean 'm.com_clean', m.com_ev 'm.com_ev', m.com_es 'm.com_es',m.com_maintain 'm.com_maintain', m.com_insur 'm.com_insur', m.com_labor 'm.com_labor', m.com_water 'm.com_water', m.com_ele 'm.com_ele' from ele e, water w, unit u , main m where w.no = e.no and e.no =u.no group by e.no ) as t");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" error :  subMainAllCount");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return result;
	}

	//
	public ArrayList<Sub> subSelectAll(Connection conn,
			Map<String, Object> pagingValues) throws SQLException {
		ArrayList<Sub> SubMixed = new ArrayList<>();
		SubPaging subPaging = (SubPaging) pagingValues.get("paging");

		if (pagingValues.get("search") != null) {
			String search = (String) pagingValues.get("search");
			pstmt = conn.prepareStatement(
					"select * from ( select w.no test , w.water_ind 'w.water_ind', e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind' from water w, ele e where w.no = e.no and e.no =w.no group by e.no "
							+ "	 having w.no like ? or w.water_ind like ? or  e.ele_basic  like ? or e.ele_ind  like ? "
							+ "	  ) as t limit ? offset ?");
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setString(4, "%" + search + "%");
			pstmt.setInt(5, subPaging.getLimit());
			pstmt.setInt(6,
					subPaging.getLimit() * (subPaging.getCurrentPage() - 1));
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							rs.getInt(4));
					SubMixed.add(sub);
				}
			} catch (Exception e) {
				e.getMessage();
				System.out.println(" error :  subSelectAll");
			}
		} else {
			System.out.println(subPaging.getLimit() + " ,"
					+ subPaging.getLimit() + ", " + subPaging.getCurrentPage());
			pstmt = conn.prepareStatement(
					"select * from ( select w.no test , w.water_ind 'w.water_ind',e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind' from ele e, water w where w.no = e.no group by e.no ) as t limit ? offset ? ");
			pstmt.setInt(1, subPaging.getLimit());
			pstmt.setInt(2,
					subPaging.getLimit() * (subPaging.getCurrentPage() - 1));
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							rs.getInt(4));
					SubMixed.add(sub);
				}
			} catch (Exception e) {
				System.out.println(" error :  subSelectAll");
				e.getMessage();
			}
		}
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		return SubMixed;
	}

	public int subAllCount(Connection conn, String search) {
		int result = 0;
		try {
			if (search != null) {
				System.out.println("subAllCount - if");
				pstmt = conn.prepareStatement(
						"select count(t.test) from ( select w.no test , w.water_ind 'w.water_ind', e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind' from ele e, water w where w.no = e.no group by e.no "
								+ "	 having w.no like ? or w.water_ind like ? or  e.ele_basic  like ? or e.ele_ind  like ? "
								+ "	  ) as t ");
				pstmt.setString(1, "%" + search + "%");
				pstmt.setString(2, "%" + search + "%");
				pstmt.setString(3, "%" + search + "%");
				pstmt.setString(4, "%" + search + "%");
			} else {
				System.out.println("subAllCount - else");
				pstmt = conn.prepareStatement(
						"select count(t.test) from ( select w.no test , w.water_ind 'w.water_ind', e.ele_basic 'e.ele_basic', e.ele_ind 'e.ele_ind' from ele e, water w where w.no = e.no group by e.no "
								+ " ) as t ");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" error :  subAllCount");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return result;
	}

}
