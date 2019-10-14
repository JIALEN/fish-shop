package com.alen.auth.mapper;



import com.alen.auth.mapper.entity.MeiteAppInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AppInfoMapper {

	@Insert("INSERT INTO `fish_app_info` VALUES (null,#{appName}, #{appId}, #{appSecret}, '0', null, null, null, null, null);")
	public int insertAppInfo(MeiteAppInfo meiteAppInfo);

	@Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  FROM fish_app_info where app_id=#{appId} and app_secret=#{appSecret}; ")
	public MeiteAppInfo selectByAppInfo(@Param("appId") String appId, @Param("appSecret") String appSecret);

	@Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  FROM fish_app_info where app_id=#{appId}  ")
	public MeiteAppInfo findByAppInfo(@Param("appId") String appId);
}
