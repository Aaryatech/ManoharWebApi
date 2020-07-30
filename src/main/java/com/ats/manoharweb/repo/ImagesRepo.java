package com.ats.manoharweb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.manoharweb.models.Images;


@Repository
public interface ImagesRepo extends JpaRepository<Images, Integer> {

	public List<Images> findByDocIdAndDocTypeAndDelStatus(int docId,int type, int del);
	
	public List<Images> findBydocIdAndDelStatus(int docId, int del);

	@Transactional
	@Modifying
	@Query(value="delete from mn_images WHERE images_id=:imageId",nativeQuery=true)
	public int deleteByImageId(@Param("imageId") int imageId);
	
	public List<Images> findAllByDelStatus(int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `mn_images` SET del_status=1 WHERE doc_id=:catId",nativeQuery=true)
	public int deleteImageByCatId(@Param("catId") int catId);
	
}
