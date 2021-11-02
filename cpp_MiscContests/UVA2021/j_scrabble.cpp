#include "bits/stdc++.h"
using namespace std;

string dict[279500];
int df[279500][31];

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr);

    int D; cin >> D;
    for (int i = 0; i < D; i++) {
        cin >> dict[i];
        for (char c : dict[i]) df[i][c-97]++;
    }

    int T; cin >> T;
    while (T--) {
        int I, J; cin >> I >> J;
        string si, sj;
        vector<int> fi(26, 0), fj(26, 0);
        for (int i = 0; i < I; i++) { 
            char c; cin >> c; 
            si += c;
            fi[c-97]++;
        }
        for (int j = 0; j < J; j++) {
            char c; cin >> c;
            sj += c;
            fj[c-97]++;
        }
        
        string ansi = "", ansj = "";
        int swapi = -1, swapj= -1;
        for (int d = 0; d < D; d++) {
            bool worksi = true, worksj = true;
            for (int l = 0; l < 26; l++) {
                if (df[d][l] > fi[l]) worksi = false;
                if (df[d][l] > fj[l]) worksj = false;
            }

            if (worksi) {
                if (dict[d].length() > ansi.length()) ansi = dict[d];
                if (dict[d].length() == ansi.length() && dict[d] > ansi) ansi = dict[d];
            }
            if (worksj) {
                if (dict[d].length() > ansj.length()) ansj = dict[d];
                if (dict[d].length() == ansj.length() && dict[d] > ansj) ansj = dict[d];
            }
        }

        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                fi[si[i]-97]--; fi[sj[j]-97]++;
                fj[si[i]-97]++; fj[sj[j]-97]--;

                string checki = "", checkj = "";
                for (int d = 0; d < D; d++) {
                    bool worksi = true, worksj = true;
                    for (int l = 0; l < 26; l++) {
                        if (df[d][l] > fi[l]) worksi = false;
                        if (df[d][l] > fj[l]) worksj = false;
                    }

                    if (worksi) {
                        if (dict[d].length() > checki.length()) checki = dict[d];
                        if (dict[d].length() == checki.length() && dict[d] > checki) checki = dict[d];
                    }
                    if (worksj) {
                        if (dict[d].length() > checkj.length()) checkj = dict[d];
                        if (dict[d].length() == checkj.length() && dict[d] > checkj) checkj = dict[d];
                    }
                }

                if (checki.length() - checkj.length() > ansi.length() - ansj.length()) {
                    ansi = checki; ansj = checkj;
                    swapi = i; swapj = j;
                }
                if (checki.length() - checkj.length() == ansi.length() - ansj.length()) {
                    if (checki > ansi) {
                        ansi = checki; ansj = checkj;
                        swapi = i; swapj = j;
                    }
                    else if (checkj < ansj) {
                        ansi = checki; ansj = checkj;
                        swapi = i; swapj = j;
                    }
                }
                // if (checki.length() - checki.length() != ansi.length() - ansj.length()) {
                //     if (ansi.length() < ansj.length() ) {
                //         if (checki.length() - checkj.length() > ansi.length() - ansj.length()) {
                //             ansi = checki; ansj = checkj;
                //             swapi = i; swapj = j;
                //         }
                //     }
                //     else if (checki.length() - checkj.length() >= 0 && checki.length() - checkj.length() > ansi.length() - ansj.length()) {
                //         ansi = checki; ansj = checkj;
                //         swapi = i; swapj = j;
                //     }
                // }
                // else {
                //     if (checki > ansi) {
                //         ansi = checki; ansj = checkj;
                //         swapi = i; swapj = j;
                //     }
                //     else if (checkj < ansj) {
                //         ansi = checki; ansj = checkj;
                //         swapi = i; swapj = j;
                //     }
                // }

                fi[si[i]-97]++; fi[sj[j]-97]--;
                fj[si[i]-97]--; fj[sj[j]-97]++;
            }
        }

        if (swapi == -1 && swapj == -1) cout << "DON'T SWAP" << "\n";
        else cout << si[swapi] << " " << sj[swapj] << "\n";
    }

    return 0;
}