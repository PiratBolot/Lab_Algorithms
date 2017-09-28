#include <fstream>
#include <string>
#include <iostream>

using namespace std;
 
int getHashCode(string s);
 
const int HASHCONST = 100000;
 
template<class T>
class LinkedListNode
{
public:
    LinkedListNode(T value, LinkedListNode<T>& next, LinkedListNode<T>& prev)
    {
        this->Value = value;
        this->Next = &next;
        this->Previous = &prev;      
    }
 
    LinkedListNode() {}
 
    T Value;
    LinkedListNode* Next;
    LinkedListNode* Previous;
};
 
template<class T>
class LinkedList
{
public:
    LinkedList()
    {
        header = new LinkedListNode<T>();
        header->Next = header->Previous = header;
        size = 0;
    }
 
    void Add(T item)
    {
        if (Contains(item) == false) {
            LinkedListNode<T>* newNode = new LinkedListNode<T>(item, *header, *header->Previous);
            newNode->Previous->Next = newNode;
            newNode->Next->Previous = newNode;
            size++;
        }
    }
 
    bool Contains(T item)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter->Value != item && iter->Next != header) {
                iter = iter->Next;
            }
            if (iter->Value == item) {
                return true;
            }
        }
        return false;
    }
 
    void Remove(T item)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter->Next != header && iter->Value != item) {
                iter = iter->Next;
            }
            if (iter->Value == item) {
                iter->Previous->Next = iter->Next;
                iter->Next->Previous = iter->Previous;
                iter->Next = NULL;
                iter->Previous = NULL;               
                size--;
            }
        }
    }
 
private:
    LinkedListNode<T>* header;
    int size;
};
 
int main()
{
    ios_base::sync_with_stdio(false);
    ifstream reader("set.in", ios_base::in);
    ofstream writer("set.out", ios_base::trunc);    
    LinkedList<string>* list = new LinkedList<string>[HASHCONST];
    string line;    
    string number;  
    while (!reader.eof()) {
        reader >> line;
        reader >> number;
        if (line.compare("+") != 0)
        {
            if (line.compare("insert") == 0) {
                list[getHashCode(number)].Add(number);
            }
            else if (line.compare("delete") == 0) {
                list[getHashCode(number)].Remove(number);
            }
            else if (line.compare("exists") == 0)
            {
                if (list[getHashCode(number)].Contains(number))
                {
                    writer << "true" << endl;
                }
                else
                {
                    writer << "false" << endl;
                }
            }           
        }
        line = "+";
    }
    reader.close();
    writer.close();
}   
 
int getHashCode(string s) {
    int ans = 0;
    for (int i = 0; i < s.length(); i++) {
        ans += 31 * ans + s[i];
        ans %= HASHCONST;
    }   
    return ans;
}
