package com.vm.ps.vmproject_ps;

import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    Map<String, Integer> variables = new HashMap<>();

    public void reset_param(ListView memList) {
        variables.clear();
        reg.clearAll();
        refreshMem(memList);
    }
    public int read(String code, ListView memList){
        reset_param(memList);
        Scanner sc = new Scanner(code);
        while(sc.hasNext()){
            boolean stopFound = false;
            String codeLine = sc.nextLine();
            String[] cmdLine = codeLine.split(" ");
            for(int i=0; i<cmdLine.length; i++){
                if(!cmdLine[i].isBlank() || !cmdLine[i].isEmpty())
                    mem.cmdMemory.add(cmdLine[i].trim().toLowerCase());
                if(cmdLine[i].trim().toLowerCase().equals("stop")){
                    stopFound = true;
                    break;
                }
            }
            if(stopFound)
                break;
        }
        allocateVariables(sc, memList);
        run();
        return 0;
    }
    public void allocateVariables(Scanner sc, ListView memList) {
        while (sc.hasNext()) {
            String varLine = sc.nextLine();
            if (!varLine.isEmpty()) {
                String[] allocLine = varLine.split(" ");
                variables.put(allocLine[0].toLowerCase(), mem.nextEmptyPosition(allocLine[2]));
            }
        }
        sc.close();
        refreshMem(memList);
    }
}
