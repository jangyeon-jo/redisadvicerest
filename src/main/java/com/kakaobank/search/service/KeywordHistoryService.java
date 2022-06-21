package com.kakaobank.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaobank.search.model.BestKeywordResult;
import com.kakaobank.search.repo.KeywordHistoryRepository;
import com.kakaobank.search.repo.entity.KeywordHistory;
import lombok.RequiredArgsConstructor;

/**
 * KeywordHistory Service
 * @author JJY
 *
 */

@RequiredArgsConstructor
@Service
public class KeywordHistoryService {
	
	@Autowired
	private KeywordHistoryRepository keywordHistoryRepository;
	
	/**
	 * Create KeywordHistory
	 * @param keywordHIstory
	 * @return
	 */
	public boolean createKeywordHistory(KeywordHistory keywordHIstory){
		try{
			keywordHistoryRepository.save(keywordHIstory);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Get All KeywordHistory
	 * @return
	 */
	public List<KeywordHistory> getAllKeywordHistory(){
		return keywordHistoryRepository.findAll();
	}
	
	/**
	 * Get KeywordHistory
	 * @param seq
	 * @return
	 */
	public Optional<KeywordHistory> getKeywordHistory(Long seq){
		return keywordHistoryRepository.findById(seq);
	}
	
	/**
	 * Update KeywordHistory
	 * @param seq
	 * @param keywordHistory
	 * @return
	 */
	public KeywordHistory updateKeywordHistory(Long seq, KeywordHistory keywordHistory){
		final Optional<KeywordHistory> fetchedKeywordHistory = keywordHistoryRepository.findById(seq);
		if(fetchedKeywordHistory.isPresent()){
			keywordHistory.setSeq(seq);
			return keywordHistoryRepository.save(keywordHistory);
		}
		else{
			return null;
		}
	}
	
	/**
	 * Patch KeywordHistory
	 * @param seq
	 * @param keywordHistory
	 * @return
	 */
	public KeywordHistory patchKeywordHistory(Long seq, KeywordHistory keywordHistory){
		final Optional<KeywordHistory> fetchedKeywordHistory = keywordHistoryRepository.findById(seq);
		if(fetchedKeywordHistory.isPresent()){
			if(keywordHistory.getKeyword() != null){
				fetchedKeywordHistory.get().setKeyword(keywordHistory.getKeyword());
			}
			if(keywordHistory.getSearch_date() != null){
				fetchedKeywordHistory.get().setSearch_date(keywordHistory.getSearch_date());
			}
			if(keywordHistory.getCount() > 0){
				fetchedKeywordHistory.get().setCount(keywordHistory.getCount());
			}
			return keywordHistoryRepository.save(fetchedKeywordHistory.get());
		}
		else{
			return null;
		}
	}
	
	/**
	 * Delete KeywordHistory
	 * @param seq
	 * @return
	 */
	public boolean deleteKeywordHistory(Long seq){
		final Optional<KeywordHistory> fetchedKeywordHistory = keywordHistoryRepository.findById(seq);
		if(fetchedKeywordHistory.isPresent()){
			keywordHistoryRepository.deleteById(seq);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<BestKeywordResult> bestKeywordCountAll(){
		List<Map> list = keywordHistoryRepository.vestKeywordCountAll();
		List<BestKeywordResult> result = new ArrayList<BestKeywordResult>();
		BestKeywordResult data = null;
		for(int idx =0; idx < list.size(); idx++) {
			data = new BestKeywordResult();
			data.setRank(idx+1);
			data.setKeyword((String)list.get(idx).get("KEYWORD"));
			data.setCount(Long.parseLong(list.get(idx).get("COUNT").toString()));
			
			result.add(data);
		}
		
		return result;
	}
}