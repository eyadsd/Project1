package com.company;

import java.util.*;

public class AStar {
    PriorityQueue<Schedule> pqueue ;
    HashSet<Schedule> visited = new HashSet<Schedule>();
    List<Schedule> acceptedStates;
    public AStar()
    {
        pqueue = new PriorityQueue<Schedule>(1, new Comparator<Schedule>() {
            public int compare(Schedule node1, Schedule node2) {
                if (node1.getWeight() < node2.getWeight())
                    return 1;
                else if (node1.getWeight() > node2.getWeight())
                    return -1;
                return 0;
            }

        });
        acceptedStates = new ArrayList<Schedule>();
    }
    public void Search(Schedule state)
    {
        List<Schedule> possibleStates;
        Schedule v;
        pqueue.add(state);
        visited.add(state);
        while(!pqueue.isEmpty())
        {
            v = pqueue.poll();
            if(v.isAccepted())
            {
                acceptedStates.add(v);
            }
            for (Schedule schedule : possibleStates = v.getPossibleNextMoves()) {
                if(!visited.contains(schedule))
                {
                    pqueue.add(schedule);
                    visited.add(schedule);
                }
            }
            ;
        }
    }

}