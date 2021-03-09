package com.wade.yourstore;


import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@EnableAsync
@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		SpringApplication.run(StoreApplication.class, args);
		setSystemTray();
	}


	public static void setSystemTray() throws MalformedURLException {
		// 判断是否支持系统托盘
		System.setProperty("java.awt.headless", "false");
		if (SystemTray.isSupported())
		{
			// 实例化图像对象
			ImageIcon icon = new ImageIcon("xx.png");
			// 获得Image对象
			Image image = icon.getImage();
			// 创建托盘图标
			TrayIcon trayIcon = new TrayIcon(image);
			Desktop desktop=Desktop.getDesktop();
			// 为托盘添加鼠标适配器
			trayIcon.addMouseListener(new MouseAdapter()
			{
				// 鼠标事件
				@SneakyThrows
				public void mouseClicked(MouseEvent e)
				{
					// 判断是否双击了鼠标
					if (e.getClickCount() == 2 )
					{
						desktop.browse(new URI("http://localhost:8080/index.html"));
					}
				}
			});
			// 添加工具提示文本
			trayIcon.setToolTip("运行中");
			// 创建弹出菜单
			PopupMenu popupMenu = new PopupMenu();
			MenuItem item1 =new MenuItem("open");
			item1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						desktop.browse(new URI("http://localhost:8080/index.html"));
					} catch (IOException | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			popupMenu.add(item1);
			MenuItem item2 =new MenuItem("setting");
			item2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						desktop.browse(new URI("http://localhost:8080/index.html"));
					} catch (IOException | URISyntaxException e) {

						e.printStackTrace();
					}
				}
			});
			popupMenu.add(item2);
			MenuItem item3 =new MenuItem("exit");
			item3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			popupMenu.add(item3);


			// 为托盘图标加弹出菜弹
			trayIcon.setPopupMenu(popupMenu);
			// 获得系统托盘对象
			SystemTray systemTray = SystemTray.getSystemTray();
			try
			{
				// 为系统托盘加托盘图标
				systemTray.add(trayIcon);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "not support");
		}

	}






}
