package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.consol;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;

public class PathRandomNpcBuild {
    Location pathloc;
    public PathRandomNpcBuild(Location pathloc) {
        this.pathloc = pathloc;
    }
    public void pathBuild() {
        Location s_add = pathloc.add(5,0,0);
        Location w_add = pathloc.add(0,0,5);
        Location n_add = pathloc.add(-5,0,0);
        Location e_add = pathloc.add(0,0,-5);
        Location randompath = random(s_add,w_add,n_add,e_add);
        if(randompath==null) {
            consol.send("길 생성에 실패됨");
            return;
        }
        Castle_Path path = new Castle_Path(randompath);
        path.build();


    }
    public Location random (Location s,Location w,Location n, Location e) {

        ArrayList box = new ArrayList();
        if(pathtrue(s)) {
            box.add(s);
        }
        if(pathtrue(w)) {
            box.add(w);
        }
        if(pathtrue(n)) {
            box.add(n);
        }
        if(pathtrue(e)) {
            box.add(e);
        }
        Collections.shuffle(box);
        if(box.get(0)==null) {
            return null;
        }
        return (Location)box.get(0);



    }
    public boolean pathtrue(Location pathloc) {
        int y = pathloc.getWorld().getHighestBlockAt(pathloc).getY();
        int path1 = pathloc.getBlockY();
        int path2 = pathloc.getBlockY();
        int path3 = pathloc.getBlockY();
        int newy = near_answer(path1,path2,path3,y);
        if(near_min(path1,path2,path3,y)&&heightCheck(pathloc)) {
            return true;
        }
        return false;
    }
    public boolean heightCheck(Location loc) {


        for (int i = 0; i == 11; i++) {
            Location downloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 5 + i, loc.getBlockZ());
            if (structureHashMap.protect.containsKey(downloc) || structureHashMap.Link.containsKey(downloc)) {
                return false;
            }

        }
        return true;

    }
    public int near_answer(int a , int b , int c, int near) {
        // 근사값 알고리즘(Near Algorithm)
        // 차이값의 절대값의 최소값(전체 데이터 중 특정 데이터와 가장 근접한 값)
        int min = Integer.MAX_VALUE; // 차이값의 절대값 최소값 저장

        int[] arr = {a,b,c};
        int target = near; // 해당 데이터와 가장 근접한 값을 찾아야 함
        int answer = 0;

        //
        for(int i = 0; i < arr.length; i++) {
            int abs = Math.abs(arr[i] - target); // 차이값의 절대값 반환
            if(abs < min) { // 절대값이 최소값보다 작다면
                min = abs; // 최소값 교체
                answer = arr[i]; // 정답
            }
        }
        return answer;

        //System.out.println(target + "과 가장 가까운 값 : " + answer);
        //System.out.println(target + "과 가장 가까운 값의 차이 : " + min);
    }
    public boolean near_min(int a , int b , int c, int near) {
        // 근사값 알고리즘(Near Algorithm)
        // 차이값의 절대값의 최소값(전체 데이터 중 특정 데이터와 가장 근접한 값)
        int min = Integer.MAX_VALUE; // 차이값의 절대값 최소값 저장

        int[] arr = {a,b,c};
        int target = near; // 해당 데이터와 가장 근접한 값을 찾아야 함
        int answer = 0;

        //
        for(int i = 0; i < arr.length; i++) {
            int abs = Math.abs(arr[i] - target); // 차이값의 절대값 반환
            if(abs < min) { // 절대값이 최소값보다 작다면
                min = abs; // 최소값 교체
                answer = arr[i]; // 정답
            }
        }
        if(min >=3 ) {
            return false;
        }
        return true;

        //System.out.println(target + "과 가장 가까운 값 : " + answer);
        //System.out.println(target + "과 가장 가까운 값의 차이 : " + min);
    }

}
