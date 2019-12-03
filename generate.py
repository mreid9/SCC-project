import random

def main():
  open("input/massive.txt", "w").close()
  f = open("input/massive.txt", "a+")
  f.write("1000\n")
  for i in range(1500):
    f.write("%d %d\n" % ((random.randrange(499)), (random.randrange(499))))
  f.close()
  open("input/BigAndStronglyConnected.txt", "w").close()
  f = open("input/BigAndStronglyConnected.txt", "a+")
  f.write("1000\n")
  for i in range(999):
    f.write("%d %d\n" % (i, i+1))
  f.write("999 0")
  f.close()
if __name__ == '__main__':
    main()
