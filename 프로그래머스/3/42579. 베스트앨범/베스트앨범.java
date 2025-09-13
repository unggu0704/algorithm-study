import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        int answerIndex = 0;
        List<Integer> answerList = new ArrayList<>();


        Map<Genre, List<Music>> albumMap = new HashMap<>();
        List<Genre> genreList;
        int inputSize = genres.length;

        for (int i = 0; i < inputSize; i++) {
            Genre genre = new Genre(genres[i], 0);
            Music music = new Music(i, plays[i]);

            albumMap.computeIfAbsent(genre, k -> new ArrayList<>()).add(music);
        }

        // 플레이 순대로 정렬
        genreList = new ArrayList<>(albumMap.keySet());
        for (Genre genre : genreList) {
            List<Music> musicList = albumMap.get(genre);
            Collections.sort(musicList);
            for (Music m : musicList) {
                genre.playCount += m.playCount;
                System.out.print(m.number + " ");
            }
            System.out.println();
        }

        //playCount에 따른 genreList 정렬
        Collections.sort(genreList);

        for (Genre genre : genreList) {
            List<Music> musicList = albumMap.get(genre);
            int size = Math.min(musicList.size(), 2);

            for (int i = 0; i < size; i++) {
                answerList.add(musicList.get(i).number);
            }
        }

        answer = new int[answerList.size()];

        for (Integer i : answerList) {
            answer[answerIndex++] = i;
        }

        return answer;
    }
}

class Genre implements Comparable<Genre> {
    String genreName;
    int playCount;

    Genre(String genreName, int playCount) {
        this.genreName = genreName;
        this.playCount = playCount;
    }

    @Override
    public int compareTo(Genre g) {
        return g.playCount - this.playCount;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Genre && this.genreName.equals(((Genre) obj).genreName);
    }

    @Override
    public int hashCode() {
        return this.genreName.hashCode();
    }
}


class Music implements Comparable<Music> {
    int number;
    int playCount;

    Music (int number, int playCount) {
        this.number = number;
        this.playCount = playCount;
    }

    @Override
    public int compareTo(Music o) {
        return o.playCount - this.playCount;
    }
}