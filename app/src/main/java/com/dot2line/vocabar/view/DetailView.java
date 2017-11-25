package com.dot2line.vocabar.view;

import com.dot2line.vocabar.base.BaseMVPView;
import com.dot2line.vocabar.model.VocaPair;

import java.util.ArrayList;

public interface DetailView extends BaseMVPView {

  void setBookTitle(String bookTitle);
  void setPairList(ArrayList<VocaPair> pairs);
}
