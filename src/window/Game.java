package window;

import entity.Fur;
import entity.GameObject;
import entity.Player;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import manage.KeyManager;
import manage.MouseManager;
import world.Level;


public class Game implements Runnable {


    private Display display;
    public MouseManager mouseManager;


	
	public int width, height;
	public String title;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean running = false;

	public KeyManager keyManager;

	public ArrayList<GameObject> listRpgObject = new ArrayList<GameObject>();
	public ArrayList<GameObject> listRemoveObjects = new ArrayList<>();

	public Player player = new Player(this, 30, 30);
	//Fur fur = new Fur(this, 300, 300);
	public Level level = new Level(this);

	public int offsetX = 0;
	public int offsetY = 0;

	public void centerPlayer(int x, int y) {
		offsetX = x - width/2;
		offsetY = y - height/2;

	}

	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	//!!!!!Инициализация
	private void init() {
		display = new Display(this.title, width, height);
		keyManager = new KeyManager();

		mouseManager = new MouseManager();
		display.getCanvas().addMouseListener(mouseManager);
		display.getJFrame().addKeyListener(keyManager);
	}

	//!!!ОБНОВЛЕНИЕ
	private void move() {

        level.move();

        for (GameObject item : listRpgObject) {
        	item.move();
		}
		player.move();
        //fur.move();

		for (GameObject item : listRemoveObjects) {
			listRpgObject.remove(item);
		}

		listRemoveObjects.clear();

	}

	//!!ПРОРИСОВКА
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Рисуем!!!
		///////////////////////////////////////////////////////////////
		g.clearRect(0, 0, width, height);

		level.render(g);

		for (GameObject item : listRpgObject) {
			item.render(g);
		}
		player.render(g);
		//fur.render(g);



		///////////////////////////////////////////////////////////////


		//end draw
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		
		init(); //инициализация
		
		int fps = 60;
		double kolNS = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		//бесконечный цикл пока running
		while (running) {
			now = System.nanoTime();

			delta += (now - lastTime) / kolNS;
			lastTime = now;
			
			if (delta >= 1) {
				System.out.println("60fps");
				move();  //пересчет объектов
				render();  //перерисовка объектов
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
