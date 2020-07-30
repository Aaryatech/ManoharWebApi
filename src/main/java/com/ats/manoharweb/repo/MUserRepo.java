package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.manoharweb.models.MUser;

@Repository
public interface MUserRepo extends JpaRepository<MUser, Integer> {

	@Query(value="SELECT\n" + 
			"    user.user_id,\n" + 
			"    user.user_name,\n" + 
			"    user.user_mobile_no,\n" + 
			"    user.user_address,\n" + 
			"    user.user_email,\n" + 
			"    user.reg_date,\n" + 
			"    user.profile_pic,\n" + 
			"    user.password,\n" + 
			"    user.company_Id,\n" + 
			"    user.designation_id,\n" + 
			"    user.user_type,\n" + 
			"    user.del_status,\n" + 
			"    user.is_active,\n" + 
			"    user.ex_int1,\n" + 
			"    user.ex_int2,\n" + 
			"    user.ex_int3,\n" + 
			"    user.ex_int4,\n" + 
			"    user.ex_float1,\n" + 
			"    user.ex_float2,\n" + 
			"    user.ex_float3,\n" + 
			"    user.ex_var1,\n" + 
			"    user.ex_var2,\n" + 
			"    type.user_type_name AS ex_var3,\n" + 
			"    desig.designation AS ex_var4\n" + 
			"FROM\n" + 
			"     m_user user,\n" + 
			"     mn_user_type type,\n" + 
			"     mn_designation desig\n" + 
			"WHERE\n" + 
			"	user.company_Id=:compId AND\n" + 
			"    user.del_status=0 AND\n" + 
			"    type.del_status=0 AND\n" + 
			"    desig.del_status=0 AND\n" + 
			"    user.user_type=type.user_type_id AND\n" + 
			"    user.designation_id=desig.designation_id\n" + 
			"    ORDER BY user.user_id DESC",nativeQuery=true)
	List<MUser> findByAllCompanyId(@Param("compId") int compId);
	
	MUser findByUserIdAndDelStatusAndCompanyId(int userId, int del, int compId);
	
	MUser findByUserMobileNoAndDelStatus(String mobNo, int del);
	
	MUser findByUserMobileNoAndDelStatusAndUserIdNot(String mobNo, int del, int userId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_user SET del_status=1 WHERE user_id=:userId",nativeQuery=true)
	public int deleteUserById(@Param("userId") int userId);
	
	@Query(value="SELECT\n" + 
			"        * \n" + 
			"    FROM\n" + 
			"        m_user \n" + 
			"    WHERE\n" + 
			"        user_mobile_no = :username\n" + 
			"        AND PASSWORD = :password\n" + 
			"        AND del_status = 0 \n" + 
			"        AND is_active = 0 \n" + 
			"        AND user_type IN(\n" + 
			"            SELECT\n" + 
			"                user_type_id     \n" + 
			"            FROM\n" + 
			"                mn_user_type     \n" + 
			"            WHERE\n" + 
			"                ex_int1 IN (:type))", nativeQuery=true)
	public MUser getUserCradentials(@Param("username") String username, @Param("password") String password, @Param("type") int type);

	@Query(value="SELECT\n" + 
			"        * \n" + 
			"    FROM\n" + 
			"        m_user \n" + 
			"    WHERE\n" + 
			"        (user_mobile_no = :username \n" + 
			"        or user_email = :username) \n" + 
			"        AND del_status = 0 \n" + 
			"        AND is_active = 0 \n" + 
			"        AND user_type IN(\n" + 
			"            SELECT\n" + 
			"                user_type_id     \n" + 
			"            FROM\n" + 
			"                mn_user_type     \n" + 
			"            WHERE\n" + 
			"                ex_int1 = :type\n" + 
			"        )   ", nativeQuery=true)
	MUser forgotPassword(@Param("username") String username,  @Param("type") int type);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_user SET password=:password WHERE user_id=:userId",nativeQuery=true)
	public int updatePassword(@Param("password") String password,@Param("userId") int userId);
	
	public MUser findByUserIdAndDelStatus(int userId, int del);

	
}
