package com.liang.common.util.keyword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试类
 * @author neusoft
 *
 */
public class TestSearch {

	public static void main(String[] args) throws IOException {
		long start =System.currentTimeMillis();
		
		InputStreamReader isr=new InputStreamReader(new FileInputStream("C:\\Users\\neusoft\\Desktop\\text.txt"),"GBK");
		//FileReader reader=new FileReader("C:\\Users\\neusoft\\Desktop\\text.txt");
		
		BufferedReader br = new BufferedReader(isr);

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		//System.out.println(sb.toString());
		

		InputStreamReader keywordisr=new InputStreamReader(new FileInputStream("C:\\Users\\neusoft\\Desktop\\keyword.txt"),"GBK");
		//FileReader reader=new FileReader("C:\\Users\\neusoft\\Desktop\\text.txt");
		
		BufferedReader keywordbr = new BufferedReader(keywordisr);

		List<String> keywords=new ArrayList<>();
		String keywordline;
		while ((keywordline = keywordbr.readLine()) != null) {
			keywords.add(keywordline);
		}

		//构建过滤器
		KeywordFilterBuilder builder = new KeywordFilterBuilder();
		builder.setKeywords(keywords);
		builder.setSkipChars(Arrays.asList('*', ' '));
		KeywordFilter filter = builder.build();

		
		//查找是否存在关键词
		boolean have=filter.hasKeywords(sb.toString());
		System.out.println(have);


		//统计关键词数量
		for (int i = 0; i < keywords.size(); i++) {
			int count=filter.count(sb.toString(), keywords.get(i));
			System.out.println(keywords.get(i)+"总共找到"+count+"个");

		}
		
		//测试替换
		final ReplaceStrategy ss = new ReplaceStrategy() {
			@Override
			public String replaceWith(String keywords) {
				String result="";
				
				for (int i = 0; i < keywords.length(); i++) {
					result+="*";
				}
				return result;
			}
		};
		
		//有用，这个是替换关键词的方法

		String result=filter.replace(sb.toString(), ss);
		System.out.println(result);

		
		long end=System.currentTimeMillis();
		
		System.out.println("查询耗时："+(end-start)+"ms");
		
		br.close();
	}

	
}
