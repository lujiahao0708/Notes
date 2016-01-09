package com.lujiahao.querycontacts;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * 读取手机联系人的工具类
 * 
 * @author chiahao
 * 
 */
public class QueryContactUtils {
	/**
	 * 读取手机联系人的方法
	 * 
	 * @param context
	 *            调用该方法处的上下文
	 * @return 存储联系人的JavaBean的List集合
	 */
	public static List<Contact> readContact(Context context) {

		List<Contact> contactLists = new ArrayList<Contact>();
		// 1.先查询raw_contacts表，查询contact_id列
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");

		Cursor cursor = context.getContentResolver().query(uri,
				new String[] { "contact_id" }, null, null, null);
		while (cursor.moveToNext()) {
			String contact_id = cursor.getString(0);

			if (contact_id != null) {
				// 创建contact对象
				Contact contact = new Contact();
				// 设置id
				contact.setId(contact_id);

				// 2.根据contact_id去查询data表，data表中对应的是raw_contact_id，查询data1和mimetype_id列
				// 但是这里要注意的是，其实并不会查询mimetype_id，因为根本没有查询data表，而是查询的是data表和mimetypes表的视图
				// 因此里面查询的是mimetype
				Cursor dataCursor = context.getContentResolver().query(dataUri,
						new String[] { "data1", "mimetype" },
						"raw_contact_id=?", new String[] { contact_id }, null);
				while (dataCursor.moveToNext()) {
					String data1 = dataCursor.getString(0);
					String mimetype = dataCursor.getString(1);

					// 3.根据mimetype类型来区分data1的数据
					if ("vnd.android.cursor.item/name".equals(mimetype)) {
						// 将姓名存入JavaBean对象中
						contact.setName(data1);
					} else if ("vnd.android.cursor.item/phone_v2"
							.equals(mimetype)) {
						// 将电话号码存入JavaBean对象中
						contact.setPhone(data1);
					} else if ("vnd.android.cursor.item/email_v2"
							.equals(mimetype)) {
						// 将email存入JavaBean对象中
						contact.setEmail(data1);
					}
				}
				// 把contact对象存入到集合中
				contactLists.add(contact);
			}
		}
		return contactLists;
	}

}
